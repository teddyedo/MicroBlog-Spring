package com.example.microblog.controller;

import java.security.SecureRandom;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * UserController
 */

@Controller
public class UserController {

    @Autowired
    UserRepository repo;
    

    @PostMapping(value="Microblog/registration")
    public String registration(Utente u) {
        
        Random random = new SecureRandom();
                
        byte[] SaltGeneration = new byte[16];
        random.nextBytes(SaltGeneration);
        
        String salt = DatatypeConverter.printBase64Binary(SaltGeneration);
        String passwordEncrypted = u.getPassword() + salt;
        
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(passwordEncrypted, Charsets.UTF_8);
        String sha256 = hasher.hash().toString();

        u.setPassword(sha256);
        u.setSALT(salt);
        u.setLivello("1");

        repo.save(u);
        
        return  "redirect:/Microblog";
    }

    @PostMapping(value = "Microblog/loginform")
    public String userLogin(String username, String password, HttpServletRequest request){

        Utente u = repo.findByUsername(username);

        Hasher hasher = Hashing.sha256().newHasher();

        String passwordEncrypted = u.getPassword();
        String salt = u.getSALT();

        String newPassword = password + salt;

        hasher.putString(newPassword, Charsets.UTF_8);
        String sha256 = hasher.hash().toString();

        System.out.println(u.getPassword());
        System.out.println(sha256);
     

        if(sha256.equals(passwordEncrypted)){
            HttpSession session = request.getSession();
            session.setAttribute("Username", username);
            System.out.println(u.getUsername());

            return "redirect:/Microblog/listaPost";
        }
        
        return null;
    }
    
}

