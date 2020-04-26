package com.example.microblog.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

/**
 * 
 * @author Allari Edoardo
 * @version 1.0.0 - 14/03/2020
 * 
 */

@Entity
public class Commento extends RepresentationModel<Commento> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date dataOra;

    @Basic
    @Getter
    @Setter
    private String testo;


    @Getter
    @Setter
    @ManyToOne(targetEntity = Utente.class, cascade = CascadeType.ALL)
    private Utente utente;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Post.class, cascade = CascadeType.ALL)
    private Post post;

    public Commento() {
    }

    public Commento(long id, Date dataOra, String testo, Utente utente, Post post) {
        this.id = id;
        this.dataOra = dataOra;
        this.testo = testo;
        this.utente = utente;
        this.post = post;
    }

	

}