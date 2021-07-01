package com.example.server.RepositoryInterFace;

import com.example.server.Model.DemandeRA;
import com.example.server.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DemandeRARespository extends MongoRepository<DemandeRA,String> {
    List<DemandeRA> findByUser(User user);
    @Query (value = "{'Treat':false}")
    List<DemandeRA> demandeNoTreated();

}
