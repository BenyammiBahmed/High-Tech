package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.RepositoryInterFace.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PcBuldingService {
    @Autowired
    ArticleRepository repository;
    public List<Article> matherboard(String id){
        Article article=repository.findByCodeModle(id);
        if(article!=null)
        {
            String socket= (String) article.getPreporite().get("socket");
            return repository.listMotherBoard(socket);

        }
        return null;
    }
    public List<Article> ram(String id){
        Article article=repository.findByCodeModle(id);
        if(article!=null)
        {
            String ramType= (String) article.getPreporite().get("RAMTechnology");
            return repository.listRAM(ramType);

        }
        return null;
    }
}
