package com.example.microblog.restRepository;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Allari Edoardo
 * Rest repository for posts
 */

@Api(tags = "Post Entity")
@RepositoryRestResource(collectionResourceRel="posts", path="posts", exported = true)
public interface RepoPost extends PagingAndSortingRepository<Post, Long> {

    @ApiOperation("Find a post by a user")
    List<Post> findByUser(@ApiParam(value = "The user that wrote the post") @RequestBody User u);

    @ApiOperation("Find a post by a user id")
    @Query(value = "SELECT * " +
            "FROM POST p " +
            "INNER JOIN USER u ON u.ID = p.USER_ID " +
            "WHERE p.USER_ID = ?1", nativeQuery = true)
    List<Post> findPostsByUser_Id(@ApiParam(value = "The id of the user that wrote the post") Long id);

}
