package com.example.microblog.entities;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


/**
 * 
 * @author Allari Edoardo
 * @version 1.0.0 - 14/03/2020
 * 
 */

@Entity
public class Utente extends RepresentationModel<Utente>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(unique = true, nullable = false)
    @Basic
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Basic
    @Getter
    @Setter
    private String Password;

    @Column(unique = true, nullable = false)
    @Basic
    @Getter
    @Setter
    private String Email;

    @Column(nullable = false)
    @Basic
    @Getter
    @Setter
    private String SALT;

    @Column(nullable = false)
    @Basic
    @Getter
    @Setter
    private String Roles;

    public Utente() {
    }

    public Utente(long id, String username, String password, String email, String sALT, String roles) {
        this.Id = id;
        this.username = username;
        this.Password = password;
        this.Email = email;
        this.SALT = sALT;
        this.Roles = roles;
    }



}
