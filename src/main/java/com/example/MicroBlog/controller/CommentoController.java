package com.example.microblog.controller;

import java.util.Date;
import java.util.List;

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
 */
@Controller
public class CommentoController {

    @Autowired
    UserRepository repoU;

    @Autowired
    PostRepository repoP;

    @Autowired
    CommentoRepository repoC;

    @RequestMapping("Microblog/listaPost/creaCommento")
    public String getCommentoFormPage(HttpSession session, HttpServletRequest request) {

        if (session != null && session.getAttribute("username") != null) {

            Utente u = repoU.findByUsername((String) session.getAttribute("username"));

            Iterable<Post> listaPost = repoP.findAll();
            Post post = null;

            for (Post p : listaPost) {
                if (request.getParameter(String.valueOf(p.getId())) != null) {
                    post = p;
                    break;
                }
            }

            session.setAttribute("post", post);
            return "creaCommento.html";
        } else {
            return "utenteNonAutorizzato.html";
        }

    }

    @RequestMapping("Microblog/listaPost/creaCommento/publicCommento")
    public String publicCommento(Commento c, HttpSession session) {

        Date dataOra = new Date();

        c.setDataOra(dataOra);

        Utente u = repoU.findByUsername((String) session.getAttribute("username"));
        c.setUtente(u);
        
        c.setPost((Post) session.getAttribute("post"));

        repoC.save(c);

        return "redirect:/Microblog/listaPost";
    }
}