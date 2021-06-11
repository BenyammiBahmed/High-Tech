package com.example.server.Controller;

import com.example.server.Model.Addresse;
import com.example.server.Model.Image;
import com.example.server.RepositoryInterFace.ImageRespository;
import com.example.server.Services.LoginService;
import com.example.server.Model.User;
import com.example.server.Services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Optional;


@Controller
public class GeneralController {

    @Autowired
    LoginService loginService;
    @Autowired
    CompteService registrate;
    @Autowired
    ImageRespository imageRespository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("error", "dddd");
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("MessageError", message);
        return "Login";
    }

    @GetMapping("/login")
    public String login(@RequestParam("email") String email, Model model, @RequestParam("password") String password, HttpSession session) {
        User user = loginService.login(email, password);
        if (user == null) {
            model.addAttribute("MessageError", "Email has exist ! go to login");
            return "Login";
        } else {
            session.setAttribute("user", user);
            return "redirect:/" + user.getRole().toString();
        }
    }

    @RequestMapping(value = "/getPhoto/{id}", method = RequestMethod.GET)
    public @ResponseBody
    void getPhoto(@PathVariable String id, HttpServletResponse response) {
        System.out.println("ok");
        try {
            Optional<Image> opImage = imageRespository.findById(id);
            Image image = opImage.get();
            byte[] imagenEnBytes = image.getImage().getData();
            response.setHeader("Accept-ranges", "bytes");
            response.setContentType("image/jpeg");
            response.setContentLength(imagenEnBytes.length);
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Description", "File Transfer");
            response.setHeader("Content-Transfer-Encoding", "binary");

            OutputStream out = response.getOutputStream();
            out.write(imagenEnBytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block

        }
    }
}
