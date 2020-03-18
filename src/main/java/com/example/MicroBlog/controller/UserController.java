package com.example.microblog.controller;

import com.example.microblog.entities.Utente;

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

    @RequestMapping("Microblog/login")
    public String getLoginPage() {
        return "login.html";
    }

    @RequestMapping("Microblog/logout")
    public String getLogoutPage(Utente u) {
        return "logout.html";
    }
}

