package com.example.microblog.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.microblog.entities.Utente;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * MyUserDetails
 */
public class MyUserDetails implements UserDetails {

    private long id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Utente utente){
        this.id = utente.getId();
        this.username = utente.getUsername();
        this.password = utente.getPassword();
        this.salt = utente.getSALT();
        this.email = utente.getEmail();
        this.authorities = Arrays.stream(utente.getRoles().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}