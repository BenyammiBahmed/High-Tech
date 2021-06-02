package com.example.server.RepositoryInterFace;

import com.example.server.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'email' : ?0 },{'password': ?0 }")
    User findByEmail(String email,String password);
}
