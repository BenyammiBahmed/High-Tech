package com.example.server.Services;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.example.server.ServerClass.Order;
import com.paypal.api.payments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaymentService {
    @Autowired
    private APIContext apiContext;
    public Payment createPayment(Order order, String url) throws PayPalRESTException{
        Amount amount = new Amount();
        amount.setCurrency(order.getCurrency());
        double total = new BigDecimal(order.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(String.format("%.2f", total).replace(",","."));
        amount.setTotal(String.format("%.2f", total).replace(",","."));

        Transaction transaction = new Transaction();
        transaction.setDescription(order.getDescription());
        CartBase cartBase = transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent(order.getIntent());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(url+"/USER/ValidPayment");
        redirectUrls.setReturnUrl(url+"/USER/PannierPage");
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
//    public String chekCard(UserCard userCard,double price){
//        RestTemplate rt = new RestTemplate();
//
//        rt.getMessageConverters().add(new StringHttpMessageConverter());
//        String url="http://localhost:8081/payment";
//
//        Map<String, Object> vars = new HashMap<String, Object>();
//        vars.put("price",price);
//        vars.put("firstName",userCard.getFirstName());
//        vars.put("lastName",userCard.getLastName());
//        vars.put("cardNumbre",userCard.getCardNumbre());
//        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
//        vars.put("dateExpiration",dateFormat.format(userCard.getDateExpiration()));
//        vars.put("CVV",userCard.getCVV());
//
//       String reponse= rt.postForObject(url, vars, String.class);
//        return reponse;
//    }
//    public String validPayment(String idConfirmation,String codeConfirmation){
//        RestTemplate rt = new RestTemplate();
//        rt.getMessageConverters().add(new StringHttpMessageConverter());
//        String url="http://localhost:8081/validPayment";
//        Map<String, String > vars = new HashMap<String, String>();
//        vars.put("idConfirmation",idConfirmation);
//        vars.put("codeConfirmation",codeConfirmation);
//
//        String reponse= rt.postForObject(url, vars, String.class);
//        return reponse;
//    }
}
