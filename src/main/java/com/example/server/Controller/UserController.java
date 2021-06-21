package com.example.server.Controller;

import com.example.server.Model.Addresse;
import com.example.server.Model.Article;
import com.example.server.Model.User;
import com.example.server.Model.WaitList;
import com.example.server.RepositoryInterFace.ArticleRepository;
import com.example.server.RepositoryInterFace.UserRepository;
import com.example.server.RepositoryInterFace.WaitListRespository;
import com.example.server.ServerClass.Panier;
import com.example.server.ServerClass.UserCard;
import com.example.server.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/USER")
public class UserController {
    @Autowired
    LoginService login;
    @Autowired
    PanierService panierService;
    @Autowired
    CompteService compteService;
    @Autowired
    WaitListService waitListService;

    @Autowired
    ArticleService articleService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PaymentService paymentService;
    @Autowired
    SearchService searchService;
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/Account")
    public String account(Model model, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "";
    }

    @PostMapping("/Update")
    public String update(User user, Addresse addresse, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        user.setAddresse(addresse);
        compteService.CompteUpdate(user);
        return "redirect:/USER/Account";
    }

    @PostMapping("/ReserveArticle")
    public String ReserveArticle(Article article, int quntityReserved, Model model, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";

        boolean b = panierService.reservedActicle(article, quntityReserved, session);
        String message;
        if (b)
            message = "article is reserved for 15 minutes";

        else message = "not quntity enough";
        model.addAttribute("message", message);
        return "redirect:/";

    }

    @PostMapping("/WaitArticle")
    public String WaitArticle(Article article, Model model, int quntityAttend, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";

        User user = (User) session.getAttribute("user");
        waitListService.insertWaitList(article, user, quntityAttend);
        model.addAttribute("message", "");
        return "redirect:/";
    }

    @PostMapping("/Payment")
    public String payment(UserCard userCard, Double price, Model model, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        String reponse = paymentService.chekCard(userCard, price);
        if (reponse.equals("invalid cord")) {
            model.addAttribute("messageError", reponse);
            //return to panier page
            return "";
        } else {
            //return to valid payment page
            session.setAttribute("codeConfirmation", reponse);
            return "";
        }
    }

    @PostMapping("/ValidPayment")
    public String validPayment(@RequestParam("code") String code, Model model, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        String reponse = paymentService.validPayment((String) session.getAttribute("codeConfirmation"), code);
        if (reponse.equals("ok")) {
            panierService.validPanier((Panier) session.getAttribute("pannier"), (User) session.getAttribute("user"));
            model.addAttribute("message", "payment valid");
            return "redirect:/";

        } else {
            model.addAttribute("messageError", "incrrect code");
            return "";
        }
    }

    @GetMapping("/PannierPage")
    public String pannierPage(HttpSession session, Model model) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        Panier panier = (Panier) session.getAttribute("pannier");
        model.addAttribute("pannier", panier);
        return "";
    }

    @PostMapping("/LibreArticle/[id]")
    public String LibreArticle(@PathVariable String id, HttpSession session) {
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        Panier panier = (Panier) session.getAttribute("pannier");
        if (panier != null)
            panierService.supprimeIthem(panier, id);
        return "redirect:/PannierPage";
    }
    @GetMapping("/WaitList")
    public String WaitList(HttpSession session,Model model){
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
        User user= (User) session.getAttribute("user");
        model.addAttribute("waitList",waitListService.waitListsUser(user));
        return "";
    }
    @PostMapping("UpDateWaitList")
    public String UpDateWaitList(@RequestParam("id") String id,@RequestParam("quntity") int quntity,HttpSession session){
        if (!login.chekUser(session))
            return "redirect:/LoginPage";
    }

//    @GetMapping("/test")
//    @ResponseBody
//    public Map<Article, Integer> user() throws MessagingException, IOException {
//        User user = new User();
//        user.setPassword("555");
//        user.setEmail("555");
//        user.setFirstName("ddd");
//        user = userRepository.save(user);
//        Article article = articleRepository.findByCodeModle("ds");
//        WaitList waitList = new WaitList();
//        waitList.setQuantity(5);
//        waitList.setArticle(article);
//        waitList.setUser(user);
//        waitListRespository.save(waitList);
//
//        return waitListService.articleWaitList();
//
//    }

}
