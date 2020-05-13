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

import javax.servlet.http.HttpSession;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;


/**
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

    @RequestMapping("Microblog/posts")
    /**
     * Generate and view the postList
     * @return HTML page - postlist page
     */
    public String getListaPost(Model model) {

        model.addAttribute("listaPost", repoP.findAll());
        model.addAttribute("commentoRepo", repoC);

        return "postList.html";
    }

    @RequestMapping("Microblog/posts/creapost")
    /**
     * get the page to create a post
     * @return HTML page - creaPost page
     */
    public String getPostForm(){
        return "creaPost.html";
    }

    
    @RequestMapping("Microblog/posts/newpost")
    /**
     * Create a new post
     * @param p: post JSON formatted
     * @param session: the session of the user
     * @return HTML page - postList page
     */
    public String publicPost(Post p, HttpSession session) {
        
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
