//package com.example.microblog.security;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//import com.example.microblog.entities.Utente;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// * UserPrincipal
// */
//public class UserPrincipal implements UserDetails {
//
//    private Utente user;
//
//    public UserPrincipal(Utente user) {
//        super();
//        this.user = user;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return Arrays.stream(user.getRoles().split(","))
//                     .map(SimpleGrantedAuthority::new)
//                     .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        // TODO Auto-generated method stub
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        // TODO Auto-generated method stub
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//
//
//}