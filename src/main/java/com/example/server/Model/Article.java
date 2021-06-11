package com.example.server.Model;

import lombok.Data;
import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Article {
    @Id
    private String codeModele;
    private String name;
    private ArticleType type;
    private Double price;
    private String mark;
    private int quantity;
    private Date lastModification;
    private boolean isBlocked=false;
    private String imagesIda;
    private JSONObject preporite;
    public String getCodeModele() {
        return codeModele;
    }

    public void setCodeModele(String codeModele) {
        this.codeModele = codeModele;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getMark() {
        return mark;
    }

    public String getImagesIda() {
        return imagesIda;
    }

    public void setImagesIda(String imagesIda) {
        this.imagesIda = imagesIda;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }
}
