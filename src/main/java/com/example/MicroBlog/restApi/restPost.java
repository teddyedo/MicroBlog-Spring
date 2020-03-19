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

/**
 * restPost
 */

@RestController
@RequestMapping("posts")
public class restPost {

    @Autowired
    PostRepository repoP;

    /**
     * 
     * @return a list with all the posts
     */

    @GetMapping
    public List<Post> getUsers() {

        return repoP.findAll();
    }

    /**
     * 
     * @param id
     * @return the Post with the Id given, if it exists
     */

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Post>> getUser(@PathVariable("id") long id) {

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

    @PostMapping
    public ResponseEntity createUser(Post post) {

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

    @PutMapping(value = "{id}")
    public ResponseEntity modifyUser(@PathVariable("id") Long id, @RequestBody Post post) {

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
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long id) {
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