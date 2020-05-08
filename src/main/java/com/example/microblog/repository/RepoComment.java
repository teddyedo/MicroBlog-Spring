package com.example.microblog.repository;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Comment Entity")
@RepositoryRestResource(collectionResourceRel="comments", path="comments")

public interface RepoComment extends PagingAndSortingRepository<Comment, Long> {

    @ApiOperation("Find a comment by a post")
    List<Comment> findByPost(@ApiParam(value = "The post that is linked to the comment") @RequestBody Post p);

    @ApiOperation("Find a comment by a post id")
    List<Comment> findCommentsByPost_Id(@ApiParam(value = "The id of the post that is linked to the comment") Long id);

    @ApiOperation("Find a comment by a user")
    List<Comment> findByUser(@ApiParam(value = "The user that wrote the comment") @RequestBody User u);

    @ApiOperation("Find a comment by a user id")
    List<Comment> findCommentsByUser_Id(@ApiParam(value = "The id of the user that wrote the comment") Long id);
}
