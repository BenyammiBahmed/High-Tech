package com.example.server.Services;

import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.AddresseRepository;
import com.example.server.RepositoryInterFace.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    UserRepository repository;
    @Autowired
    AddresseRepository addresseRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public User Registrate(User user) {
        if (repository.findEmail(user.getEmail()))
            return null;
        else {
            if ((user.getAddresse() != null) )
                user.setAddresse(addresseRepository.save(user.getAddresse()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = repository.save(user);
            return user;
        }
    }
    public User CompteUpdate(User user){
       Optional<User> userop = repository.findById(user.getIdUser());
       if (!userop.isEmpty())
       {
           user.setPassword(userop.get().getPassword());
          return repository.save(user);
       }
       return null;
    }
    public User LoadUserInfo(String email){
        return repository.findByEmail(email);
    }

}