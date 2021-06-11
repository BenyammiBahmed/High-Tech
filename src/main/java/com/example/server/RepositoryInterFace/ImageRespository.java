package com.example.server.RepositoryInterFace;

import com.example.server.Model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRespository extends MongoRepository<Image,String> {
}
