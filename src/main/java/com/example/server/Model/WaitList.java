package com.example.server.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class WaitList {
    private String idWaitList;
    @DBRef
    private User user;
    @DBRef
    private Article article;
    private int quantity;

    public WaitList() {
    }

    public WaitList(User user, Article article, int quntity) {
        this.user = user;
        this.article = article;
        this.quantity = quntity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdWaitList() {
        return idWaitList;
    }

    public void setIdWaitList(String idWaitList) {
        this.idWaitList = idWaitList;
    }

}
