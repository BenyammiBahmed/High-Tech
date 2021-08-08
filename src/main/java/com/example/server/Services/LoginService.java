package com.example.server.Services;

import com.example.server.Model.Roles;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    @Autowired
    UserRepository repository;
    @Autowired
    CryptoService cryptoService;


    public boolean chekAdmin(HttpSession session){
        User user= (User) session.getAttribute("user");
        return (user != null) && (user.getRole().equals(Roles.ADMIN));
    }
    public boolean chekUser(HttpSession session){
        User user= (User) session.getAttribute("user");
        return (user != null) && (user.getRole().equals(Roles.USER));
    }

}
