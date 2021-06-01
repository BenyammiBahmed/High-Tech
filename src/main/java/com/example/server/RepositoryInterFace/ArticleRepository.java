package com.example.server.RepositoryInterFace;

import com.example.server.Model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article,String> {
}
