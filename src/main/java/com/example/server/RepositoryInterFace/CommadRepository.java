package com.example.server.RepositoryInterFace;

import com.example.server.Model.Command;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommadRepository extends MongoRepository<Command,String> {
    @Query(value = "{'isDelivred': false}")
    List<Command> CommandNoDelivred();
    @Query(value = "{ 'itemList':{'$ref' : 'commandItem','$id' :{'$oid': ?0}}}")
    Command findByIthem(String idThem);
}
