package com.example.server.Model;

import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="article")

public class Article {
    @Id
    private String codeModle;
    private String name;
    private ArticleType type;
    private Double prix;
    private String mark;
    private int quantity;
    private JSONObject preporite;
    public String getCodeModle() {
        return codeModle;
    }

    public void setCodeModle(String codeModle) {
        this.codeModle = codeModle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public JSONObject getPreporite() {
        return preporite;
    }

    public void setPreporite(JSONObject preporite) {
        this.preporite = preporite;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
