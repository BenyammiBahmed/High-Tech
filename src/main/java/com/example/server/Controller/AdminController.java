package com.example.server.Controller;

import com.example.server.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/ADMIN")
public class AdminController {
    @Autowired
    LoginService loginService;
    @GetMapping("")
    public String AdminIndex(HttpSession session, Model model) {
        if (loginService.chekAdmin(session))
            return "Admin/index";
        else
            return "Login";

    }
}
