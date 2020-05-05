package com.example.microblog.springDataRest;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="comments", path="comments")
public interface RepoComment extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findByPost(Post p);
    List<Comment> findByUser(User u);
    List<Comment> findCommentsByPost_Id(Long id);
    List<Comment> findCommentsByUser_Id(Long id);
}
