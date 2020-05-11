package com.example.microblog.entities;

import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.RepresentationModel;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author Allari Edoardo
 * @version 1.0.0 - 14/03/2020
 * 
 */
@ApiIgnore
@Entity
public class Post extends RepresentationModel<Post> {

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

    @Basic
    @Getter
    @Setter
    private String titolo;

    @Getter
    @Setter
    @ManyToOne(targetEntity = User.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Post() {
    }

    public Post(long id, Date dataOra, String testo, String titolo) {
        this.id = id;
        this.dataOra = dataOra;
        this.testo = testo;
        this.titolo = titolo;
    }

}