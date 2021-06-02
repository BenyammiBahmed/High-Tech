package com.example.server.Controller;

import com.example.server.Model.Addresse;
import com.example.server.Services.LoginService;
import com.example.server.Model.User;
import com.example.server.Services.RegistrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class GeneralController {

    @Autowired
    LoginService loginService;
    @Autowired
    RegistrateService registrate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        User user = loginService.login(email, password);
        if (user == null)
            return "Login";
        else {
            session.setAttribute("user", user);
            return "redirect:/" + user.getRole().toString();
        }
    }

    @GetMapping("/RegistratePage")
    public String RegistratePage() {
        return "Registrate";
    }

    @PostMapping("/Registrate")
    public String Registrate(User user, Addresse addresse, HttpSession session, Model model) {
        user.setAdresse(addresse);
       user= registrate.saveUser(user);
       if(user!= null)
       {
           session.setAttribute("user",user);
           return "redirect:/" + user.getRole().toString();
       }
       else {
           model.addAttribute("MessageError","Email has exist ! go to login");
        return "Registrate";}
    }
}
