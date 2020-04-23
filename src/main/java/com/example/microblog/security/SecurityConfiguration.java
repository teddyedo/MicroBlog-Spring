//package com.example.microblog.security;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
///**
// * SecurityConfiguration
// */
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Qualifier("myUserDetailService")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return provider;
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//            .authorizeRequests()
//                .antMatchers("/Microblog/posts/c*").hasRole("ADMIN")
//                .antMatchers("Microblog/comments/c*").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/", "resources/static/css", "resources/static/fonts", "resources/static/js", "resources/static/vendor", "/h2/**").permitAll()
//                .and()
//            .formLogin()
//                .loginPage("/Microblog/login")
//                .and()
//            .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutUrl("/Microblog/logout")
//                .logoutSuccessUrl("/Microblog/login")
//                .and()
//                .headers()
//                .frameOptions()
//                .disable()
//                .and()
//                .csrf()
//                .disable();
//
//
//
//    }
//
//}