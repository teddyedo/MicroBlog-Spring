/*package com.example.microblog.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfiguration
 */
/*
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema()
            .withUser(
                User.withUsername("user")
                .password("pass")
                .roles("USER")
            )
            .withUser(
                User.withUsername("admin")
                .password("admin")
                .roles("ADMIN")
            );
    }




    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        http.authorizeRequests()
            .antMatchers("/Microblog/posts/n*").hasRole("ADMIN")
            .antMatchers("Microblog/comments/n*").hasAnyRole("USER", "ADMIN")
            .antMatchers("/", "resources/static/css", "resources/static/fonts", "resources/static/js", "resources/static/vendor").permitAll()
            .and().formLogin();
    }
}*/