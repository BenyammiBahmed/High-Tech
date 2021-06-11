package com.example.server.Controller;

import com.example.server.Model.Article;
import com.example.server.Services.ArticleService;
import com.example.server.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/ADMIN")
public class AdminController {
    @Autowired
    LoginService loginService;
    @Autowired
    ArticleService articleService;

    @GetMapping("")
    public String AdminIndex(HttpSession session, Model model) {
        if (loginService.chekAdmin(session))
            return "Admin/index";
        else
            return "Login";

    }
    @PostMapping("addArticle")
    public String addArticle(Article article , @RequestParam Map<String,?> properity, MultipartFile image){
        properity.remove("image");
        articleService.saveArticle(article,properity,image);
        return "ok";
    }
}
