package com.example.server.Controller;

import com.example.server.Model.*;
import com.example.server.RepositoryInterFace.ImageRespository;

import com.example.server.Services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Optional;


@Controller
public class GeneralController {
    @Autowired
    CompteService registrate;
    @Autowired
    ImageRespository imageRespository;
    @Autowired
    SearchService searchService;

    @GetMapping("/")
    public String index(Model model) {


        model.addAttribute("result", searchService.newArticles());
        return "index";
    }

//    @GetMapping("/Logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("user");
//        return "redirect:/";
//    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String message, Model model) {

        return "Login";
    }

    @GetMapping("/Registrate")
    public String RegistratePage() {

        return "Registrate";
    }

    @PostMapping("/Registrate")
    public String Registrat(User user, Addresse addresse, Model model, HttpSession session) {
        user.setRole(Roles.USER);
        System.out.println("user");
        user.setAddresse(addresse);
        user = registrate.Registrate(user);
        if (user == null || user.getIdUser() == null) {
            model.addAttribute("MessageError", "email hsa already exite");
            return "redirect:/Registrate";
        } else
            return "redirect:/";
    }

    @RequestMapping("/loginError")
    public String loginError(Model model) {
        System.out.println("fat mana");
        model.addAttribute("loginError", true);
        return "Login";
    }

    //    @GetMapping("/Login")
//    public String login(@RequestParam("email") String email, Model model, @RequestParam("password") String password, HttpSession session) {
//        User user = loginService.login(email, password);
//        System.out.println(user==null);
//        if (user == null) {
//            model.addAttribute("MessageError", "Email or password is incorrect");
//            return "Login";
//        } else {
//            session.setAttribute("user", user);
//            if (user.getRole().equals(Roles.ADMIN))
//                return "redirect:/" + user.getRole().toString();
//            else return "redirect:/";
//        }
//    }
    @GetMapping("/SuccessLogin")
    public String defaultAfterLogin(Principal principal,HttpSession session) {

       User user= registrate.LoadUserInfo(principal.getName());
       if (user.getRole().equals(Roles.ADMIN))
           return "redirect:/ADMIN";
       session.setAttribute("user",user);
        return "redirect:/";
    }


    @GetMapping("/Article/{id}")
    public String articleId(@PathVariable String id, Model model) {
        System.out.println(id);
        model.addAttribute("article", searchService.articleById(id));
        return "Product_details";

    }

    @RequestMapping(value = "/getPhoto/{id}", method = RequestMethod.GET)
    public @ResponseBody
    void getPhoto(@PathVariable String id, HttpServletResponse response) {

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
