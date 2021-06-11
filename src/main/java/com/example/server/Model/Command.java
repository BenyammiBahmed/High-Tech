package com.example.server.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Data
public class Command {
    @Id
    private String idCommand;
    @DBRef
    private User user;
    private Date date;
    private boolean isDelivred;
    @DBRef
    private List<CommandItem> itemList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDelivred() {
        return isDelivred;
    }

    public void setDelivred(boolean delivred) {
        isDelivred = delivred;
    }
}
