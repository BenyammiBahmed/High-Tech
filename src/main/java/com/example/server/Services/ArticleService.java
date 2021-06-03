package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.RepositoryInterFace.ArticleRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    ArticleRespository articleRespository;
    public boolean saveArticle(Article article, Map<String,?> properity){
        JSONObject object= new JSONObject(properity);
        article.setPreporite(object);
        
        return (articleRespository.save(article)!=null);
    }
    public boolean updateArticle(Article article, Map<?,?> properity){
        JSONObject object= new JSONObject((Map<String, ?>) properity);
        article.setPreporite(object);
       article= articleRespository.save(article);
        return (article!=null);
    }
    private Map<?,?> removeClassProperity(Map<?,?> properity){
        Field[] fileds=Article.class.getFields();
        for (Field f :fileds)
            properity.remove(f.toString());
        return properity;
    }
    private boolean chekExist(){
        return true;
    }

}
