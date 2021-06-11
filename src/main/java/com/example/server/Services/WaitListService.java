package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.Model.User;
import com.example.server.Model.WaitList;
import com.example.server.RepositoryInterFace.WaitListRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitListService {
    @Autowired
    WaitListRespository respository;


    public void insertWaitList(Article article, User user,int quntity){
        WaitList waitList=respository.findByArticleAndUser(article,user);
        if((waitList==null)||(waitList.getIdWaitList()==null))
         waitList =new WaitList(user,article,quntity);
        else
            waitList.setQuntity(quntity);
        respository.save(waitList);
    }
    public boolean ArticleExit(Article article){
        return respository.exitArticle(article);
    }

    public void sendEmail(Article article){
        List<User> waitList=respository.findUserWaitArticle(article);
    }
}
