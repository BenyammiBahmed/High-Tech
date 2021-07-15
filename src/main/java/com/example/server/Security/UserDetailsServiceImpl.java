package com.example.server.Security;

import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =repository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserDoto(user);
    }
}
