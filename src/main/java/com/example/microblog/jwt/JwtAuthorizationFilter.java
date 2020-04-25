package com.example.microblog.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.microblog.entities.Utente;
import com.example.microblog.repository.UserRepository;
import com.example.microblog.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Read the authorization header
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        //If header doesn't contain BEARER or is null, delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        //If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {

        String token = request.getHeader(JwtProperties.HEADER_STRING);
        if (token != null) {
            //parse the token and validate it
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            //Search in the DB if there is a user by token subject (username)
            //if exists, grab user details and create spring auth token using username, password and roles
            if (username != null) {
                Optional<Utente> op = userRepository.findUtenteByUsername(username);
                Utente utente = op.get();
                UserPrincipal principal = new UserPrincipal(utente);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
