package com.example.microblog.restApi;

import com.example.microblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * restUtente
 * @author Allari Edoardo
 * 
 */

@RequestMapping("Microblog/api/users")
@RestController
public class restUtente {

    @Autowired
    UserRepository repoU;

    @RequestMapping()
    public 
    
    
}