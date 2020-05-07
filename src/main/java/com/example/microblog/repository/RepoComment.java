package com.example.microblog.repository;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Api(tags = "Comment Entity")
@RepositoryRestResource(collectionResourceRel="comments", path="comments")

public interface RepoComment extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findByPost(Post p);
    List<Comment> findByUser(User u);
    List<Comment> findCommentsByPost_Id(Long id);
    List<Comment> findCommentsByUser_Id(Long id);
}
