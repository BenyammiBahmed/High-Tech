package com.example.server.Controller;

import com.example.server.Services.LoginService;
import com.example.server.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;


@Controller
public class GeneralController {

    @Autowired
    LoginService loginService;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "Login";
    }
    @GetMapping("/login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session) {
        User user = loginService.login(email, password);
        if (user == null)
            return "Login";
        else {
            session.setAttribute("user",user);
            return "redirect:/" + user.getRole().toString();

        }
    }
//    @GetMapping ("/test")
//    @ResponseBody
//    public List<User> test(){
//        return login.login("benyammibahmed@gmail.com", "1234");
//    }
}
