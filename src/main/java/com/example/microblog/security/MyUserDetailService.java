package com.example.microblog.security;

import com.example.microblog.entities.Utente;
import com.example.microblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * MyUserDetailService
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username){

        Optional<Utente> user = userRepository.findUtenteByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found; " + username));

        return new MyUserDetails(user.get());
    }

}