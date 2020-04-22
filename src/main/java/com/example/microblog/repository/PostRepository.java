package com.example.microblog.repository;

import com.example.microblog.entities.Post;

import com.example.microblog.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * PostRepository
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUtente(Utente u);
}

