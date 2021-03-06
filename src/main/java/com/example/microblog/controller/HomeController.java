package com.example.microblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Allari Edoardo
 * HomeController
 * This controller manage the requests sends by a local instance of Microblog related to the homepage
 */

@Controller
public class HomeController {

    /**
     * Redirect to homePage
     * @return HTML page - homepage
     */
    @RequestMapping("/")
    public String redirectHomePage(){
        return "redirect:/Microblog";
    }

    /**
     * Homepage
     */
    @RequestMapping("Microblog")
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
    @GetMapping("Microblog/logout")
    public String logout(){return "logout.html";}

    /**
     * Go to login page
     * @return HTML page - login page
     */
    @GetMapping("/login")
    public String login(){ return "login.html";}

    /**
     * Go to login-error page
     * @return HTML page - login page
     */
    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login.html";}
    
}