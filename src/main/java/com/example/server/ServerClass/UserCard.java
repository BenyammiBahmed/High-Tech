package com.example.server.ServerClass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserCard {
    private String lastName;
    private String firstName;
    private String cardNumbre;
    private String CVV;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateExpiration;
}
