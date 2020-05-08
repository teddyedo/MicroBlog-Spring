package com.example.microblog.security;

import com.example.microblog.entities.User;
import com.example.microblog.restRepository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    @Autowired
    RepoUser userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> op = userRepository.findByUsername(username);
        User u = op.get();
        UserPrincipal userPrincipal = new UserPrincipal(u);

        return userPrincipal;
    }
}
