package com.example.microblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfiguration
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/Microblog/register").permitAll()
                .antMatchers("/Microblog/registration").permitAll()
                .antMatchers("/Microblog/login").permitAll()
                .antMatchers("/Microblog/logout").permitAll()
                .antMatchers("/Microblog/posts").permitAll()
                .antMatchers("/h2").permitAll()
                .antMatchers("/h2/l**").permitAll()
                .antMatchers("/Microblog/posts/creapost").hasRole("ADMIN")
                .antMatchers("/Microblog/posts/newpost").hasRole("ADMIN")
                .antMatchers("/Microblog/comments/creacomment/{postId}").hasRole("USER")
                .antMatchers("/Microblog/comments/newcomment/{postId}").hasRole("USER")
                .and()
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
