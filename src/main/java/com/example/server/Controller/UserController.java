package com.example.server.Controller;

import com.example.server.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    LoginService login;
@GetMapping("/")
public String indexUser(HttpSession session, Model model){
    if(login.chekUser(session))
    return "index";
    else{

        model.addAttribute("MessageError","email or password incorrect");
        return "Login";
    }
}

}
