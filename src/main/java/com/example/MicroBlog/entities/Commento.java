package com.example.microblog.entities;

import java.io.Serializable;
import java.util.Date;
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
public class Commento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date DataOra;

    @Basic
    @Getter
    @Setter
    private String Testo;

    @Basic
    @Getter
    @Setter
    private String Titolo;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Utente.class)
    private Utente utente;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Post.class)
    private Post post;

    public Commento() {
    }

    public Commento(long id, Date dataOra, String testo, String titolo, Utente utente, Post post) {
        this.Id = id;
        this.DataOra = dataOra;
        this.Testo = testo;
        this.Titolo = titolo;
        this.utente = utente;
        this.post = post;
    }

}