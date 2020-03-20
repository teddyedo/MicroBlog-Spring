package com.example.microblog.restApi;

import java.util.List;
import java.util.Optional;

import com.example.microblog.entities.Post;
import com.example.microblog.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * restPost
 */

@Api("Basic operations on Posts")
@RestController
@RequestMapping("posts")
public class restPost {

    @Autowired
    PostRepository repoP;

    /**
     * 
     * @return a list with all the posts
     */
    @ApiOperation("View a list of all the posts")
    @GetMapping
    public List<Post> getPosts() {

        return repoP.findAll();
    }

    /**
     * 
     * @param id
     * @return the Post with the Id given, if it exists
     */

    @ApiOperation("View the Post with the given ID")
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Post>> getPost(@ApiParam(value = "The id of the Post that will be returned") @PathVariable("id") long id) {

        if (repoP.findById(id) != null) {

            return new ResponseEntity<Optional<Post>>(repoP.findById(id), HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 
     * @param post
     * @return Http response, created or bad request
     */

    @ApiOperation("Create a new Post")
    @PostMapping
    public ResponseEntity createPost(@ApiParam(value = "The Post that will be created")Post post) {

        if (post == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            repoP.save(post);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * 
     * @param id
     * @param post
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Modify the Post with the given ID") 
    @PutMapping(value = "{id}")
    public ResponseEntity modifyPost(@ApiParam(value = "The id of the Post that will be modified")@PathVariable("id") Long id, @ApiParam(value = "The Post with the new information")@RequestBody Post post) {

        if (repoP.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else if (repoP.findById(id) != null) {
            repoP.save(post);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

    /**
     * 
     * @param id
     * @return Http response, bad request, created or conflict
     */
    @ApiOperation("Delete the Post with the given ID")
    @DeleteMapping(value = "{id}")
    public ResponseEntity deletePost(@ApiParam(value = "The id of the Post that will be deleted") @PathVariable("id") long id) {
        if (repoP.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else if (repoP.findById(id) != null) {
            repoP.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}