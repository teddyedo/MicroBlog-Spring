package com.example.microblog.restRepository;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Post Entity")
@RepositoryRestResource(collectionResourceRel="posts", path="posts")
public interface RepoPost extends PagingAndSortingRepository<Post, Long> {

    @ApiOperation("Find a post by a user")
    List<Post> findByUser(@ApiParam(value = "The user that wrote the post") @RequestBody User u);

    @ApiOperation("Find a post by a user id")
    List<Post> findPostsByUser_Id(@ApiParam(value = "The id of the user that wrote the post") Long id);

}
