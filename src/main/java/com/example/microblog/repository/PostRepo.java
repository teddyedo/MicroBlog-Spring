package com.example.microblog.repository;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByUser(User u);


}
