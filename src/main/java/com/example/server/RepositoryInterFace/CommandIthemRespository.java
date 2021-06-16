package com.example.server.RepositoryInterFace;

import com.example.server.Model.CommandItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommandIthemRespository extends MongoRepository<CommandItem,String> {
}
