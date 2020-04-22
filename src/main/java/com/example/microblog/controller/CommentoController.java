package com.example.microblog.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.microblog.entities.Commento;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.Utente;
import com.example.microblog.repository.CommentoRepository;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CommentoController
 * This controller manage the requests sends by a local instance of Microblog related to comments
 */
@Controller
public class CommentoController {

    @Autowired
    UserRepository repoU;

    @Autowired
    PostRepository repoP;

    @Autowired
    CommentoRepository repoC;

    /**
     * Return the page for creating comment
     * @return HTML page
     */
    @RequestMapping("Microblog/comments/creacomment")
    public String creaCommento(){
        return "creaCommento.html";
    }

    /**
     * Create a new comment
     * @param c the comment, JSON formatted
     * @param session the session from where take the postID
     * @return HTML page - postList
     */
    @RequestMapping("Microblog/comments/newcomment")
    public String publicCommento(Commento c, HttpSession session) {

        Date dataOra = new Date();

        c.setDataOra(dataOra);

        Utente u = repoU.findByUsername((String) session.getAttribute("username"));
        c.setUtente(u);
        
        c.setPost((Post) session.getAttribute("post"));

        repoC.save(c);

        return "redirect:/Microblog/posts";
    }
}