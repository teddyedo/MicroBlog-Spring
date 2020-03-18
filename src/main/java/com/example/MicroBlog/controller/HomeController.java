package com.example.microblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    
    public String redirectHomePage(){
        return "redirect:/Microblog";
    }

    @RequestMapping("Microblog")

    public String getHomePage(){
        return "index.html";
    }

    @RequestMapping("Microblog/register")
    public String getRegistrationPage(){
        return "register.html";
    }

    @RequestMapping("Microblog/login")
    public String getLoginPage() {
        return "login.html";
    }

    @RequestMapping("Microblog/logout")
    public String getLogoutPage() {
        return "logout.html";
    }


    
}