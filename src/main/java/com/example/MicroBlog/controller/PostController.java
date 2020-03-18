package com.example.microblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PostController
 */

 @Controller
public class PostController {

    @RequestMapping("Microblog/vediPost")
    public String getListaPost(){
        return "postList.html";
    }
    
}
