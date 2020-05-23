package com.example.microblog.restRepository;

import com.example.microblog.entities.Comment;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Allari Edoardo
 * Rest Repository for comments
 */

@Api(tags = "Comment Entity")
@RepositoryRestResource(collectionResourceRel="comments", path="comments", exported = true)
public interface RepoComment extends PagingAndSortingRepository<Comment, Long> {

    @ApiOperation("Find a comment by a post")
    List<Comment> findByPost(@ApiParam(value = "The post that is linked to the comment") @RequestBody Post p);

    @ApiOperation("Find a comment by a post id")
    @Query(value = "SELECT * " +
            "FROM COMMENT c " +
            "INNER JOIN POST p ON p.ID = c.POST_ID " +
            "WHERE c.POST_ID = ?1", nativeQuery = true)
    List<Comment> findCommentsByPost_Id(@ApiParam(value = "The id of the post that is linked to the comment") @RequestParam Long id);

    @ApiOperation("Find a comment by a user")
    List<Comment> findByUser(@ApiParam(value = "The user that wrote the comment") @RequestBody User u);

    @ApiOperation("Find a comment by a user id")
    @Query(value = "SELECT * " +
            "FROM COMMENT c " +
            "INNER JOIN USER u ON u.ID = c.USER_ID " +
            "WHERE c.USER_ID = ?1", nativeQuery = true)
    List<Comment> findCommentsByUser_Id(@ApiParam(value = "The id of the user that wrote the comment") @RequestParam  Long id);

    }
