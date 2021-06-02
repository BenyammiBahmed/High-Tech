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

    public User login(String email, String password){
        return repository.findByEmail(email,password);
    }
    public boolean chekAdmin(HttpSession session){
        User user= (User) session.getAttribute("user");
        if((user!=null)&&(user.getRole().equals(Roles.ADMIN)))
            return true;
        else
            return false;
    }
}
