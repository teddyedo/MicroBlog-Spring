package com.example.microblog.controller;

import com.example.microblog.entities.User;

import com.example.microblog.restRepository.RepoComment;
import com.example.microblog.restRepository.RepoPost;
import com.example.microblog.restRepository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author Allari Edoardo
 * UserController
 * This controller manage the requests sends by a local instance of Microblog related to users
 */

@Controller
public class UserController {

    @Autowired
    RepoUser repoU;

    @Autowired
    RepoPost repoP;

    @Autowired
    RepoComment repoC;

    /**
     * Register a new User
     * @return HTML page - homepage
     */
    @PostMapping(value="Microblog/registration")
    public String registration(User u) {

        u.setRoles("USER");
        String plainPsw = u.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode(plainPsw));

        repoU.save(u);
        
        return  "redirect:/Microblog";
    }
}

