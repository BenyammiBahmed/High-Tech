package com.example.server.RepositoryInterFace;

import com.example.server.Model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {
    @Query(value = "{'codeModele' : ?0 }")
    Article findByCodeModle(String id);
    @Query(value = "{'isBlocked': flase}")
    Page<Article> findArticle(Pageable pageable);


}
