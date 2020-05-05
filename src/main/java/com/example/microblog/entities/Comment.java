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
public class Comment extends RepresentationModel<Comment> {

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
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Post.class)
    private Post post;

    public Comment() {
    }

    public Comment(long id, Date dataOra, String testo, User utente, Post post) {
        this.id = id;
        this.dataOra = dataOra;
        this.testo = testo;
        this.user = utente;
        this.post = post;
    }

	

}