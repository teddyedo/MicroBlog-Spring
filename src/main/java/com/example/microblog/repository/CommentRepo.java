package com.example.microblog.repository;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post p);
}
