package com.example.server.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class CommandItem {
    @Id
    private String idCommandItem;
    @DBRef
    private Article article;
    private int quantity;
    private double price;


    public String getIdCommandItem() {
        return idCommandItem;
    }

    public void setIdCommandItem(String idCommandItem) {
        this.idCommandItem = idCommandItem;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
