package com.example.microblog.repository;

import java.util.List;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;

import com.example.microblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CommentoRepository
 */
public interface CommentoRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post p);

    List<Comment> findByUtente(User u);

    void deleteByPost(Post p);
}

