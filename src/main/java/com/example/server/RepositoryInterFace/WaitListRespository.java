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
    @Query(value="{'article': ?0}", fields="{'user' : 1 ,'_id':0,'article':0,'quantity':0}")
    List<User> findUserWaitArticle (Article article);
    @Query(value = "{'article':?0 ,'user':?0}")
    WaitList findByArticleAndUser(Article article,User user);
    @Query(value = "{'user':?0}")
    List<WaitList>findByUser(User user);

////    @Query("{'_id':?0}")
//    WaitList findByID(String id);
}
