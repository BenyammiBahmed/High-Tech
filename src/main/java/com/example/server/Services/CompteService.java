package com.example.server.Services;

import com.example.server.Model.User;
import com.example.server.RepositoryInterFace.AddresseRepository;
import com.example.server.RepositoryInterFace.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CompteService {
    @Autowired
    UserRepository repository;
    @Autowired
    AddresseRepository addresseRepository;
    @Autowired
    CryptoService cryptoService;
    public User Registrate(User user) {
        System.out.println(chekExist(user));
        if (repository.findEmail(user.getEmail()))
            return null;
        else {
            if ((user.getAddresse() != null) && (!user.getAddresse().getState().equals("")))
                user.setAddresse(addresseRepository.save(user.getAddresse()));
            user.setPassword(cryptoService.Crype(user.getPassword()));
            user = repository.save(user);
            return user;
        }
    }

    private boolean chekExist(User user) {
        Example<User> example = Example.of(user);
        boolean exists = repository.exists(example);
        return exists;


    }
}