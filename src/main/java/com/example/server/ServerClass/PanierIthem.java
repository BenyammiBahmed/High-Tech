package com.example.server.ServerClass;

import com.example.server.Model.Article;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

import static com.mongodb.client.model.Filters.eq;


public class PanierIthem {
    final static Long delay=1000*60*15L;
    private TimerTask timerTask;
    private Timer timer;
    private Article article;
    private int quantity;
    public PanierIthem(Article article,int quntity ,HttpSession session){
        this.article=article;
        this.quantity =quntity;
        initTimer(session);
    }
    private void initTimer(HttpSession session){
        timerTask=new TimerTask() {
            @Override
            public void run() {
                Panier panier = (Panier) session.getAttribute("panier");
                if (panier != null) {
                    panier.getPanierIthems().remove(panierIthem());
                    libreArticle();
                }
            }
        };
        timer=new Timer();
        timer.schedule(timerTask,delay);
    }
    private PanierIthem panierIthem(){
        return this;
    }
    public void libreArticle(){
        MongoTemplate mongoOp=new MongoTemplate(MongoClients.create(), "High-tech");
        MongoCollection<Document> collection=mongoOp.getCollection("article");
        Document document= collection.find(eq("_id",article.getCodeModele())).first();
        document.append("quntity",((int)document.get("quntity"))+this.quantity);
        mongoOp.save(document,"article");

    }
    private void restimer(){
        timer.schedule(timerTask,1000*60*2);
    }

    public Timer getTimer() {
        return timer;
    }

    public Article getArticle() {
        return article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
