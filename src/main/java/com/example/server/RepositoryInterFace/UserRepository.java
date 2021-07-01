package com.example.server.RepositoryInterFace;

import com.example.server.Model.Addresse;
import com.example.server.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{$and:[{ 'email' : ?0 },{'password': ?1 }]}")
    User findByEmailAndPassword(String email, String password);
    @Query(value = "{'email' : ?0 }",exists = true)
    boolean findEmail(String email);
    @Query(value = "{'addresse':?0}")
    User user(Addresse addresse);
}
