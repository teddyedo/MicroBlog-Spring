package com.example.microblog.repository;

import com.example.microblog.entities.Post;

import org.springframework.data.repository.CrudRepository;

/**
 * PostRepository
 */
public interface PostRepository extends CrudRepository<Post, Long> {


}

