package com.example.server.Services;

import com.example.server.Model.Article;
import com.example.server.Model.User;
import com.example.server.Model.WaitList;
import com.example.server.RepositoryInterFace.WaitListRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
@EnableAsync
@Service
public class WaitListService {
    @Autowired
    WaitListRespository respository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MongoTemplate mongoTemplate;


    public void insertWaitList(Article article, User user,int quntity){
        WaitList waitList=respository.findByArticleAndUser(article,user);
        if((waitList==null)||(waitList.getIdWaitList()==null))
         waitList =new WaitList(user,article,quntity);
        else
            waitList.setQuantity(quntity);
        respository.save(waitList);
    }

    @Async
    public void sendEmail(Article article)throws MessagingException, IOException {
      List<User> waitList=respository.findUserWaitArticle(article);
        MimeMessage msg = javaMailSender.createMimeMessage();
        for (User u:waitList){
         synchronized (this){
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(u.getEmail());
        helper.setSubject("High-Tech");
        helper.setText(generatEmail(u,article), true);
        javaMailSender.send(msg);}
    }}
    private String generatEmail(User user,Article article){
        String email="  <img src=\"\">" +
                "    <h3>Hi mr"+user.getFirstName()+user.getLastName()+"</h3>" +
                "    <p>the Article" +article.getName()+
                "        is deponible now in High-tech Store\n" +
                "    </p>\n" +
                "    <div style=\"align-items: center;\">\n" +
                "        <button style=\"background-color: chocolate; padding: 15px; margin: auto; border: none; align-items: center; \">" +
                "<a href=\" http://localhost:8080/Article/" +article.getImageId()+
                "                style=\"text-decoration: none; color: wheat;\">Go Article Page</a></button>\n" +
                "    </div>";
        return email;
    }
    public Map<Article,Integer> articleWaitList(){
      List<WaitList> waitLists= respository.findAll();
        Map<Article,Integer> articleQuntityWait=new HashMap<>();
        for (WaitList w:
             waitLists) {
            Article article=w.getArticle();

            if (articleQuntityWait.containsKey(article))
                articleQuntityWait.put(article,articleQuntityWait.get(article)+w.getQuantity());
            else
                articleQuntityWait.put(article,w.getQuantity());
        }
        return articleQuntityWait;
    }
   public List<WaitList> waitListsUser(User user){
        return respository.findByUser(user);
   }
   public void upDate(String id,int quntity){
        WaitList waitList=respository.findById(id).get();
        if (waitList!=null)
        {
            waitList.setQuantity(quntity);
            respository.save(waitList);
        }
   }
   public void removeWaitList(String idWaitList){
        respository.deleteById(idWaitList);
   }
}
