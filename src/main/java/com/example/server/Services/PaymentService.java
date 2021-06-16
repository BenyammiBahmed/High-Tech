package com.example.server.Services;

import com.example.server.ServerClass.UserCard;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    public String chekCard(UserCard userCard,double price){
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(new StringHttpMessageConverter());
        String url="http://localhost:8081/payment";

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("price",price);
        vars.put("userCard",userCard);

       String reponse= rt.postForObject(url, vars, String.class);
        return reponse;
    }
    public String validPayment(String idConfirmation,String codeConfirmation){
        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new StringHttpMessageConverter());
        String url="http://localhost:8081/validPayment";
        Map<String, String > vars = new HashMap<String, String>();
        vars.put("idConfirmation",idConfirmation);
        vars.put("codeConfirmation",codeConfirmation);

        String reponse= rt.postForObject(url, vars, String.class);
        return reponse;
    }
}
