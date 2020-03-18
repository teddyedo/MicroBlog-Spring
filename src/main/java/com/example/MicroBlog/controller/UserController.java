package com.example.microblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserController
 */

 @Controller
public class UserController {

    @RequestMapping("Microblog/register")
    public String getRegistrationPage(){
        return "register.html";
    }

    @RequestMapping("Micr")
    public String metodo(Utente u) {
        return "index.html";
    }

}

