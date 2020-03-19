package com.example.microblog.repository;

import com.example.microblog.entities.Post;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PostRepository
 */
public interface PostRepository extends JpaRepository<Post, Long> {


}

