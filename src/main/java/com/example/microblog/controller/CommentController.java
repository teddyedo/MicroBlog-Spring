package com.example.microblog.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;

import com.example.microblog.repository.CommentRepo;
import com.example.microblog.repository.PostRepo;
import com.example.microblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CommentController
 * This controller manage the requests sends by a local instance of Microblog related to comments
 */
@Controller
public class CommentController {

    @Autowired
    UserRepo repoU;

    @Autowired
    PostRepo repoP;

    @Autowired
    CommentRepo repoC;

    /**
     * Return the page for creating comment
     * @return HTML page
     */
    @RequestMapping("Microblog/comments/creacomment/{postId}")
    public String creaCommento(@PathVariable(value = "postId") long id, Model model){

        model.addAttribute("postId", id);
        return "creaCommento.html";
    }

    /**
     * Create a new comment
     * @param c the comment, JSON formatted
     * @param session the session from where take the postID
     * @return HTML page - postList
     */
    @RequestMapping("Microblog/comments/newcomment/{postId}")
    public String publicCommento(@PathVariable(value = "postId") long id, Comment c, HttpSession session) {

        Date dataOra = new Date();

        c.setDataOra(dataOra);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalUsername = authentication.getName();

        Optional<User> opu = repoU.findByUsername(currentPrincipalUsername);
        User u = opu.get();
        c.setUser(u);
        
        Optional<Post> op = repoP.findById(id);
        Post p = op.get();
        c.setPost(p);

        repoC.save(c);

        return "redirect:/Microblog/posts";
    }
}