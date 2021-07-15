package com.example.server.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class ErrorHandling {
    @ExceptionHandler(NoSuchFieldError.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotFoundExeption(){
        return "NoFound";
    }
}
