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
        return "redirect:/MicroBlog";
    }

    @RequestMapping("MicroBlog")

    public String getHomePage(){
        return "index.html";
    }

    


    
}