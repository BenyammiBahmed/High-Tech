package com.example.server.Controller;

import com.example.server.Model.Article;
import com.example.server.Model.Roles;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.ArticleRepository;
import com.example.server.RepositoryInterFace.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
public class test {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/")
    public String test(){
        User user =new User("ahmed", "mella", Collections.singleton(Roles.USER));
        user.setId("55555555555");
        Article article=new Article();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("run","yse");
        article.setPreporite(jsonObject);
        articleRepository.save(article);
        repository.save(user);

        return "index";
    }
    @GetMapping("/user")
   @ResponseBody
    public List<User> user(){
       List<User> users = repository.findByEmail("benyammibahmed@gmail.com");

        return users;
    }
    @PostMapping("/visituer/registration")
    public String registration(){
        return "ok";
    }
}
