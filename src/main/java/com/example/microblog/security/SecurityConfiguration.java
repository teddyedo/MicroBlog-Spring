package com.example.microblog.security;

import com.example.microblog.jwt.JwtAuthenticationFilter;
import com.example.microblog.jwt.JwtAuthorizationFilter;
import com.example.microblog.restRepository.RepoUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfiguration
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    RepoUser userRepository;

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

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

    //Security configuration for APIs
    @Order(1)
    @Configuration
    public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter{


        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider());
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
            jwtAuthenticationFilter.setFilterProcessesUrl("/Microblog/api/login");

            http.
                    antMatcher("/Microblog/api/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilter(jwtAuthenticationFilter)
                    .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/Microblog/api/users").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/Microblog/api/users").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/Microblog/api/users").hasRole("ADMIN")
                    .antMatchers("/Microblog/api/users").permitAll()
                    .antMatchers(HttpMethod.POST, "/Microblog/api/posts").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/Microblog/api/posts").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/Microblog/api/posts").hasRole("ADMIN")
                    .antMatchers("/Microblog/api/posts").permitAll()
                    .antMatchers(HttpMethod.POST, "/Microblog/api/comments").authenticated()
                    .antMatchers(HttpMethod.PUT, "/Microblog/api/comments").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/Microblog/api/comments").authenticated()
                    .antMatchers("/Microblog/api/comments").permitAll()
                    .antMatchers("/login").permitAll();
        }
    }

    //Security configuration for MVC application
    @Order(2)
    @Configuration
    public class FormLoginConfiguration extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/Microblog/comments/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/Microblog/posts/c*").hasRole("ADMIN")
                    .antMatchers("/Microblog/posts/n*").hasRole("ADMIN")
                    .antMatchers("/Microblog/**").permitAll()
                    .antMatchers("/h2").permitAll()
                    .antMatchers("/h2/l**").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/Microblog");

            http.csrf().disable();
            http.headers().frameOptions().disable();
        }
    }
}
