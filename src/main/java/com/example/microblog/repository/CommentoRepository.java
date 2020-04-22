package com.example.microblog.repository;

import java.util.List;

import com.example.microblog.entities.Commento;
import com.example.microblog.entities.Post;

import com.example.microblog.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CommentoRepository
 */
public interface CommentoRepository extends JpaRepository<Commento, Long> {

    List<Commento> findByPost(Post p);

    List<Commento> findByUtente(Utente u);
}

