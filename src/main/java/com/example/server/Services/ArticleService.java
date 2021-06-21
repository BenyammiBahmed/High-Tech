package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.Model.Image;
import com.example.server.RepositoryInterFace.ArticleRepository;
import com.example.server.RepositoryInterFace.ImageRespository;
import net.minidev.json.JSONObject;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRespository;

    @Autowired
    ImageRespository imageRespository;
    public boolean saveArticle(Article article, Map<String, ?> properity, MultipartFile image) {
        Image photo = new Image();
        try {
            photo.setImage(
                    new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (photo.getImage() != null)
        { photo=imageRespository.save(photo);
          article.setImageId(photo.getIdImage());
        }
        LocalDateTime localTime=LocalDateTime.now();
        article.setLastModification(Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant()));
            properity = removeClassProperity(properity);
        JSONObject object = new JSONObject(properity);
        article.setPreporite(object);
        article.setMark(article.getMark().toUpperCase());
        return (articleRespository.save(article) != null);
    }

    public boolean updateArticle(Article article, Map<?, ?> properity) {
        JSONObject object = new JSONObject((Map<String, ?>) properity);
        article.setPreporite(object);
        article = articleRespository.save(article);
        return (article != null);
    }

    public boolean deletArticle(Article article) {
        article = articleRespository.findByCodeModle(article.getCodeModele());
        article.setBlocked(true);
        return (articleRespository.save(article) != null);
    }

    private Map<String, ?> removeClassProperity(Map<String, ?> properity) {
        Field[] fileds = Article.class.getDeclaredFields();
        for (Field f : fileds)
            properity.remove(f.getName());
        return properity;
    }

}
