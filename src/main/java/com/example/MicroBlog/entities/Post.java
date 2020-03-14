package com.example.microblog.entities;

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
public class Post {

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

    @ManyToOne(targetEntity = Utente.class)
    private Utente utente;

    public Post() {
    }

    public Post(long id, Date dataOra, String testo, String titolo) {
        this.Id = id;
        this.DataOra = dataOra;
        this.Testo = testo;
        this.Titolo = titolo;
    }

}