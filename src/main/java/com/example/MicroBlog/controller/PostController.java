package com.example.microblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.Utente;
import com.example.microblog.repository.CommentoRepository;
import com.example.microblog.repository.PostRepository;
import com.example.microblog.repository.UserRepository;

/**
 * PostController
 */

@Controller
public class PostController {

    @Autowired
    PostRepository repoP;

    @Autowired
    CommentoRepository repoC;

    @Autowired
    UserRepository repoU;

    @RequestMapping("Microblog/listaPost")
    public String getListaPost(Model model) {

        model.addAttribute("listaPost", repoP.findAll());
        model.addAttribute("commentoRepo", repoC);

        return "postList.html";
    }

    @RequestMapping("Microblog/listaPost/creaPost")
    public String getPostFormPage(HttpSession session) {


        System.out.println((String) session.getAttribute("username"));
        if (session != null && session.getAttribute("username") != null) {

            String username = (String) session.getAttribute("username");

            Utente u = repoU.findByUsername(username);

            if ("0".equals(u.getLivello())) {
                return "creaPost.html";

            } else {
                return "utenteNonAutorizzato.html";
            }
        } else {
            return "utenteNonAutorizzato.html";
        }

    }

    @RequestMapping("Microblog/listaPost/creaPost/publicPost")
    public String publicPost(Post p, HttpSession session) {
        
        Date dataOra = new Date();
        p.setDataOra(dataOra);
        
        Utente u = repoU.findByUsername((String) session.getAttribute("username"));

        p.setUtente(u);
        repoP.save(p);

        return "redirect:/Microblog/listaPost";
    }
}
