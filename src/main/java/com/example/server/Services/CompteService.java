package com.example.server.Services;

import com.example.server.Model.Addresse;
import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.AddresseRepository;
import com.example.server.RepositoryInterFace.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteService {
    @Autowired
    UserRepository repository;
    @Autowired
    AddresseRepository addresseRepository;

  public User Registrate(User user){

      if(repository.findEmail(user.getEmail()))
          return null;
      else {
      if ((user.getAdresse()!=null)&&(!user.getAdresse().getState().equals("")))
       user.setAdresse( addresseRepository.save(user.getAdresse()));
     user= repository.save(user);
      return user ;
  }
}}
