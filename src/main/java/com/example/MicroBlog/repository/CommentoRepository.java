package com.example.microblog.repository;

import java.util.List;

import com.example.microblog.entities.Commento;
import com.example.microblog.entities.Post;

import org.springframework.data.repository.CrudRepository;

/**
 * CommentoRepository
 */
public interface CommentoRepository extends CrudRepository<Commento, Long> {

    List<Commento> findByPost(Post p);
}

