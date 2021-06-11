package com.example.server.Model;


public class WaitList {
    private String idWaitList;
    private User user;
    private Article article;
    private int quntity;

    public WaitList() {
    }

    public WaitList(User user, Article article, int quntity) {
        this.user = user;
        this.article = article;
        this.quntity = quntity;
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

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public String getIdWaitList() {
        return idWaitList;
    }

    public void setIdWaitList(String idWaitList) {
        this.idWaitList = idWaitList;
    }

}
