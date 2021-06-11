package com.example.server.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Addresse {
    @Id
    private String idAddresse;
    private String state;
    private String city;
    private String street;
    private String ocdeZip;

    public String getIdAddresse() {
        return idAddresse;
    }

    public void setIdAddresse(String idAddresse) {
        this.idAddresse = idAddresse;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
