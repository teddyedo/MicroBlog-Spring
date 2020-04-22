package com.example.microblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 * This controller manage the requests sends by a local instance of Microblog related to the homepage
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    /**
     * Redirect to homePage
     * @return HTML page - homepage
     */
    public String redirectHomePage(){
        return "redirect:/Microblog";
    }

    @RequestMapping("Microblog")
    /**
     * Homepage
     */
    public String getHomePage(){
        return "index.html";
    }

    /**
     * Go to register page
     * @return HTML page - registration page
     */
    @RequestMapping("Microblog/register")
    public String getRegistrationPage(){
        return "register.html";
    }

    /**
     * Go to login page
     * @return HTML page - login page
     */
    @RequestMapping("Microblog/login")
    public String getLoginPage() {
        return "login.html";
    }

    /**
     * Go to logout page
     * @return HTML page - logout page
     */
    @RequestMapping("Microblog/logout")
    public String getLogoutPage() {
        return "logout.html";
    }


    
}