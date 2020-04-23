package com.example.microblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.Utente;
import com.example.microblog.repository.CommentoRepository;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UserRepository;

/**
 * PostController
 * This controller manage the requests sends by a local instance of Microblog related to posts
 */

@Controller
public class PostController {

    @Autowired
    PostRepository repoP;

    @Autowired
    CommentoRepository repoC;

    @Autowired
    UserRepository repoU;

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
        
        Optional<Utente> op = repoU.findUtenteByUsername((String) session.getAttribute("username"));


        Utente u = op.get();
        p.setUtente(u);
        repoP.save(p);

        return "redirect:/Microblog/posts";
    }
}
