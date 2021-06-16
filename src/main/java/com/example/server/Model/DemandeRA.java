package com.example.server.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class DemandeRA {
    @Id
    private String idDemandeRA;
    @DBRef
    private CommandItem commandItem;
    @DBRef
    private User user;
    private String raison;
    private String response;
    private boolean isAccepte=false;
    private boolean isTreat=false;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date datebuy;

    public DemandeRA() {
        LocalDateTime localTime=LocalDateTime.now();
        this.date=Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getIdDemandeRA() {
        return idDemandeRA;
    }

    public void setIdDemandeRA(String idDemandeRA) {
        this.idDemandeRA = idDemandeRA;
    }

    public CommandItem getCommandItem() {
        return commandItem;
    }

    public void setCommandItem(CommandItem commandItem) {
        this.commandItem = commandItem;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isAccepte() {
        return isAccepte;
    }

    public void setAccepte(boolean accepte) {
        isAccepte = accepte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
