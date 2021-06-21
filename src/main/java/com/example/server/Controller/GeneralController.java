package com.example.server.Controller;

import com.example.server.Model.*;
import com.example.server.RepositoryInterFace.ImageRespository;
import com.example.server.Services.ArticleService;
import com.example.server.Services.LoginService;
import com.example.server.Services.CompteService;
import com.example.server.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class GeneralController {

    @Autowired
    LoginService loginService;
    @Autowired
    CompteService registrate;
    @Autowired
    ImageRespository imageRespository;
    @Autowired
    SearchService searchService;
    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("result", searchService.newArticles());
        return "index";
    }

    @GetMapping("/LoginPage")
    public String loginPage(@RequestParam(required = false) String message, Model model) {

        return "Login";
    }

    @GetMapping("/RegistratePage")
    public String RegistratePage() {
        return "";
    }
    @PostMapping("/Registrate")
    public String Registrat(User user,Addresse addresse,Model model,HttpSession session){
        user.setRole(Roles.USER);
        System.out.println("user");
        user.setAddresse(addresse);
      user=registrate.Registrate(user);
      if (user==null || user.getIdUser()==null) {
          model.addAttribute("MessageError", "email hsa already exite");
          return "";
      }
      else
          return "redirect:/";
    }
    @GetMapping("/Login")
    public String login(@RequestParam("email") String email, Model model, @RequestParam("password") String password, HttpSession session) {
        User user = loginService.login(email, password);
        if (user == null) {
            model.addAttribute("MessageError", "Email or password is incorrect");
            return "Login";
        } else {
            session.setAttribute("user", user);
            if (user.getRole().equals(Roles.ADMIN))
                return "redirect:/" + user.getRole().toString();
            else return "redirect:/";
        }
    }

    @GetMapping("/search")
    public String searchArticle(String search, Model model) {
        model.addAttribute("search", search);
        model.addAttribute("result", searchService.search(search));
        return "index";
    }
    @GetMapping("/{type}")
    public String searchType(@PathVariable String type, Model model){

        model.addAttribute("result", searchService.searchType(type));
        return "index";
    }
    @GetMapping("/Article/{id}")
        public String articleId(@PathVariable String id,Model model){
        model.addAttribute("article",searchService.articleById(id));
        return "";

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
