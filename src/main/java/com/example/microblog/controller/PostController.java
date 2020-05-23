package com.example.microblog.controller;

import com.example.microblog.restRepository.RepoComment;
import com.example.microblog.restRepository.RepoPost;
import com.example.microblog.restRepository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Optional;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;


/**
 * @author Allari Edoardo
 * PostController
 * This controller manage the requests sends by a local instance of Microblog related to posts
 */

@Controller
public class PostController {

    @Autowired
    RepoUser repoU;

    @Autowired
    RepoPost repoP;

    @Autowired
    RepoComment repoC;

    /**
     * Generate and view the postList
     * @return HTML page - postlist page
     */
    @RequestMapping("Microblog/posts")
    public String getListaPost(Model model) {

        model.addAttribute("listaPost", repoP.findAll());
        model.addAttribute("commentoRepo", repoC);

        return "postList.html";
    }

    /**
     * get the page to create a post
     * @return HTML page - creaPost page
     */
    @RequestMapping("Microblog/posts/creapost")
    public String getPostForm(){
        return "creaPost.html";
    }

    
    /**
     * Create a new post
     * @param p: post JSON formatted
     * @return HTML page - postList page
     */
    @RequestMapping("Microblog/posts/newpost")
    public String publicPost(Post p) {
        
        Date dataOra = new Date();
        p.setDataOra(dataOra);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalUsername = authentication.getName();

        Optional<User> op = repoU.findByUsername(currentPrincipalUsername);

        User u = op.get();
        p.setUser(u);
        repoP.save(p);

        return "redirect:/Microblog/posts";
    }
}
