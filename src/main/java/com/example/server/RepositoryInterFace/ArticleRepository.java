package com.example.server.RepositoryInterFace;

import com.example.server.Model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {
    @Query(value = "{'codeModele' : ?0 }")
    Article findByCodeModle(String id);
    @Query(value = "{'isBlocked': false}")
    List<Article> findArticle(Pageable pageable);
    @Query(value = "{'type': ?0},{'isBlocked': false}")
    List<Article>findByType(String type,Pageable pageable);
    @Query(value = "{'isBlocked': false}")
    List<Article>articlesNotBlocked();
    @Query(value = "{$and:[{'type':'MOTHERBOARD'},{'preporite.socketCPU':?0}]}")
    List<Article>listMotherBoard(String socket);
    @Query(value = "{$and:[{'type':'RAM'},{'preporite.ramType':?0}]}")
    List<Article>listRAM(String ramType);

}
