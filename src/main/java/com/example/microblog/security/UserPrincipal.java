package com.example.microblog.security;

import com.example.microblog.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User utente;

    public UserPrincipal(User utente) {
        this.utente = utente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorityList = new ArrayList<>();

       this.utente.getRoleList().forEach(p -> {
           GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + p);
           authorityList.add(authority);
       });

       return authorityList;
    }

    @Override
    public String getPassword() {
        return this.utente.getPassword();
    }

    @Override
    public String getUsername() {
        return this.utente.getUsername();
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
