package com.example.microblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.microblog.repository.CommentoRepository;
import com.example.microblog.repository.PostRepository;


/**
 * PostController
 */

 @Controller
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    CommentoRepository repoC;

    @RequestMapping("Microblog/vediPost")
    public String getListaPost(Model model){
        
        
        model.addAttribute("listaPost", repo.findAll());
        model.addAttribute("commentoRepo", repoC);

        return "postList.html";
    }
    
}
