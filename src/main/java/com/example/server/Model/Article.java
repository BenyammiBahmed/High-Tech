package com.example.server.Model;

import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="article")

public class Article {
    @Id
    private String id;
    private String name;
    private Double prix;
    private JSONObject preporite;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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



}
