package com.example.server.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
public class Command {
    @Id
    private String idCommand;
    @DBRef
    private User user;
    private Date date;

    private boolean isDelivred=false;
    @DBRef
    private List<CommandItem> itemList;

    public Command() {
        LocalDateTime localTime=LocalDateTime.now();
        this.date=Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant());
    }

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
    public double getPriceTotal(){
        double total=0;
        for (CommandItem item:
             itemList) {
            total=total+item.getPrice()*item.getQuantity();
        }
        return total;
    }
    public boolean isNew(){
        LocalDateTime localTime=LocalDateTime.now();
        Date currentDate=Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant());
        long diffInMillies = Math.abs(date.getTime() - currentDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff <= 30;
    }
}
