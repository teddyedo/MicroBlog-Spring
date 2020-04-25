package com.example.microblog.controller;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.example.microblog.entities.Utente;
import com.example.microblog.repository.UserRepository;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * UserController
 * This controller manage the requests sends by a local instance of Microblog related to users
 */

@Controller
public class UserController {

    @Autowired
    UserRepository repo;
    

    @PostMapping(value="Microblog/registration")
    /**
     * Register a new User
     * @return HTML page - homepage
     */
    public String registration(Utente u) {

        u.setRoles("USER");
        u.setSalt("sefgegeag");
        String plainPsw = u.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode(plainPsw));

        repo.save(u);
        
        return  "redirect:/Microblog";
    }


}

