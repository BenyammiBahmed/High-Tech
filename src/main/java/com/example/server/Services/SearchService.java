package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.Model.ArticleType;
import com.example.server.RepositoryInterFace.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    public List<Article> search(String s){
        String[] words = s.split("\\s+");
        Criteria criteria=new Criteria();
        criteria.where("name").regex(s);
        ArrayList<Criteria> criteriaArrayList =new ArrayList<>();
        for (String word:words)
         criteria =makeQuery(criteria,word,criteriaArrayList);

        if (criteriaArrayList.size()!=0)
            criteria.orOperator(criteriaListToArray(criteriaArrayList));
        Query query=new Query();

        query.addCriteria(criteria);
        query.with(page());
        return  mongoTemplate.find(query,Article.class);

    }
    public List<Article> searchType(String type){
       type=type.toUpperCase();

       return articleRepository.findByType(type,page());
    }
    public List<Article> newArticles(){

        List<Article> page = articleRepository.findArticle(page());
        return page;
    }
    public Article articleById(String id){
        return articleRepository.findByCodeModle(id);
    }
    private Criteria makeQuery(Criteria criteria,String s,ArrayList<Criteria> criteriaArrayList){
        if (chekIsType(s))
            return criteria.and("type").is(s.toUpperCase());
        else
            if(chekIsMark(s))
                return criteria.and("mark").is(s.toUpperCase());
            else{
                 criteriaArrayList.add(Criteria.where("name").regex(s));
                 criteriaArrayList.add(Criteria.where("mark").regex(s));
                       return criteria;

            }
    }
    private Pageable page(){
        return PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "lastModification"));
    }
    private boolean chekIsType(String s){
        s=s.toUpperCase();
        ArticleType[] types =ArticleType.values();
        for (ArticleType type : types
        ){
            if (s.equals(type.toString()))
                return true;
        }
        return false;
    }
    private Criteria [] criteriaListToArray(ArrayList<Criteria> criteriaArrayList){
        Criteria [] criteria=new Criteria[criteriaArrayList.size()];
        for (int i=0;i<criteria.length;i++)
            criteria[i]=criteriaArrayList.get(i);
        return criteria;
    }
    private boolean chekIsMark(String s){
        String [] mark={"AMD","INTEL","NVIDIA",};
        for (String m: mark)
            if (m.equals(s.toUpperCase()))
                return true;
            return false;
    }

}
