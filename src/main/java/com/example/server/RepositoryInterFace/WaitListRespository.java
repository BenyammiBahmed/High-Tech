package com.example.server.RepositoryInterFace;

import com.example.server.Model.Article;
import com.example.server.Model.User;
import com.example.server.Model.WaitList;
import com.mongodb.client.AggregateIterable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitListRespository extends MongoRepository<WaitList,String> {
    @Query(value = "{'article':?0}",exists = true)
    boolean exitArticle(Article article);
    @Query(value="{'article': {'$ref':'article','$id':?0}}")
    List<WaitList> findUserWaitArticle (String codeModele);
    @Query(value = "{'article':{'$ref':'article','$id': ?0}} ,'user':{'$ref':'user','$id':{'$oid': ?0}}}")
    WaitList findByArticleAndUser(String article,String user);
    @Query(value = "{'user':{'$ref':'user','$id':{'$oid': ?0}}}")
    List<WaitList>findByUser(String id);
   @Query(value = "{'article':{'$ref':'article','$id': ?0}}")
    void deleteByArticle(String id);
////    @Query("{'_id':?0}")
//    WaitList findByID(String id);
}
