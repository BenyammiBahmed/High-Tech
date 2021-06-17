package com.example.server.Controller;

import com.example.server.Model.Article;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.UserRepository;
import com.example.server.RepositoryInterFace.WaitListRespository;
import com.example.server.ServerClass.UserCard;
import com.example.server.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/USER")
public class UserController {
    @Autowired
    LoginService login;
    @Autowired
    PanierService panierService;
    @Autowired
    WaitListService waitListService;
    @Autowired
    WaitListRespository waitListRespository;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PaymentService paymentService;
    @Autowired
    SearchService searchService;

    @GetMapping("/")
    public String indexUser(HttpSession session, Model model) {
        if (login.chekUser(session))
            return "index";
        else {

            return "redirect:/logiPage?message=you sould login";
        }
    }

    @PostMapping("/ReserveArticle")
    public String ReserveArticle(Article article, int quntityReserved, HttpSession session) {

        boolean b=panierService.reservedActicle(article,quntityReserved,session);
        if (b)
            return "{message:ok}";
        else return "{message:not quntity enough}";
    }

    @PostMapping("/WaitArticle")
    public String WaitArticle(Article article,int quntityAttend,HttpSession session){
        if(!login.chekUser(session))
        {
            return "redirect:/logiPage?message=you sould login";
        }
        User user= (User) session.getAttribute("user");
//       waitListService.insertWaitList(article,user,quntityAttend);
        return "ok";
    }

    @PostMapping("/Payment")
    public String payment(UserCard userCard,Double price){
      String reponse=paymentService.chekCard(userCard,price);
      if(reponse.equals("invalid code"))
          //return to panier page
          return"";
      else
          //return to valid payment page
          return "";
    }
    @GetMapping("/test")
    @ResponseBody
    public List<Article> user()  {
     return searchService.newArticles();

    }

}
