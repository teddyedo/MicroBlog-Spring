package com.example.microblog.repository;

import com.example.microblog.entities.Post;

import com.example.microblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PostRepository
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUtente(User u);

    void deleteByUtente(User u);
}

