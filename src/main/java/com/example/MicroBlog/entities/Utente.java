package com.example.microblog.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allari Edoardo
 * @version 1.0.0 - 14/03/2020
 * 
 */

@Entity
public class Utente implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(unique = true, nullable = false)
    @Basic
    @Getter
    @Setter
    private String Username;

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
    private String Livello;

    public Utente() {
    }

    public Utente(long id, String username, String password, String email, String sALT, String livello) {
        this.Id = id;
        this.Username = username;
        this.Password = password;
        this.Email = email;
        this.SALT = sALT;
        this.Livello = livello;
    }

}
