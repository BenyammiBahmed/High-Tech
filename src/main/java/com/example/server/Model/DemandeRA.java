package com.example.server.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
public class DemandeRA {
    @Id
    private String idDemandeRA;
    @DBRef
    private CommandItem commandItem;
    private String raison;
    private String response;
    private boolean isAccepte;
    private Date date;

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
