package com.example.microblog.restApi;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.microblog.entities.Commento;
import com.example.microblog.entities.Post;
import com.example.microblog.entities.Utente;
import com.example.microblog.repository.CommentoRepository;
import com.example.microblog.repository.PostRepository;

import com.example.microblog.repository.UserRepository;
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
@RequestMapping("Microblog/api/posts")
public class restPost {

    @Autowired
    PostRepository repoP;

    @Autowired
    UserRepository repoU;

    @Autowired
    CommentoRepository repoC;

    @Autowired
    restCommento restC;

    /**
     * Get the list of all posts
     * @return a list with all the posts
     */
    @ApiOperation("View a list of all the posts")
    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {

        List<Post> list = repoP.findAll();
        for (Post p : list){
            p.add(linkTo(methodOn(restPost.class).getPost(String.valueOf(p.getId()))).withSelfRel());
            p.add(linkTo(methodOn(restPost.class).getPosts()).withRel("posts"));
            p.add(linkTo(methodOn(restUtente.class).getUser((String.valueOf(p.getUtente().getId())))).withRel("user"));
        }
        return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
    }

    /**
     * Find Post by Id
     * @param id postID
     * @return the Post with the Id given, if it exists
     */

    @ApiOperation("View the Post with the given ID")
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Post>> getPost(@ApiParam(value = "The id of the Post that will be returned")
                                                  @PathVariable("id") String id) {

        Optional<Post> op = repoP.findById(Long.parseLong(id));
        if (op.isPresent()) {
            Post p = op.get();
            p.add(linkTo(methodOn(restPost.class).getPost(id)).withSelfRel());
            p.add(linkTo(methodOn(restPost.class).getPosts()).withRel("posts"));
            p.add(linkTo(methodOn(restUtente.class).getUser((String.valueOf(p.getUtente().getId())))).withRel("user"));
            return new ResponseEntity<Optional<Post>>(repoP.findById(Long.parseLong(id)), HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a new post
     * @param post JSON formatted post
     * @return Http response, created or bad request
     */

    @ApiOperation("Create a new Post")
    @PostMapping
    public ResponseEntity createPost(@ApiParam(value = "The Post that will be created")
                                     @RequestBody Post post) {

        if (post == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            Optional<Utente> op = repoU.findUtenteByUsername(post.getUtente().getUsername());
            Utente u = op.get();
            post.setDataOra(new Date());
            post.setUtente(u);
            repoP.save(post);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Modify the post with the given ID
     * @param id post id
     * @param post JSON formatted user with changes
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Modify the Post with the given ID") 
    @PutMapping(value = "{id}")
    public ResponseEntity modifyPost(@ApiParam(value = "The id of the Post that will be modified")
                                     @PathVariable("id") String id,
                                     @ApiParam(value = "The Post with the new information")
                                     @RequestBody Post post) {

        Optional<Post> op = repoP.findById(Long.parseLong(id));

        if (!op.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else{
            repoP.save(post);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * Delete the post with the given Id
     * @param id post Id
     * @return Http response, bad request, created or conflict
     */
    @ApiOperation("Delete the Post with the given ID")
    @DeleteMapping(value = "{id}")
    public ResponseEntity deletePost(@ApiParam(value = "The id of the Post that will be deleted")
                                     @PathVariable("id") String id) {

        Optional<Post> op = repoP.findById(Long.parseLong(id));

        if (!op.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            repoC.deleteByPost(op.get());
            repoP.deleteById(Long.parseLong(id));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @ApiOperation("Get all the posts made by the user with the given Id")
    @GetMapping(value = "/user/{id}")
    public ResponseEntity getPostsByUserId(@ApiParam(value = "The id of the user that made the posts")
                                           @PathVariable("id") String id){

        Optional<Utente> u = repoU.findById(Long.parseLong(id));

        if (!u.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{

            List<Post> list = repoP.findByUtente(u.get());
            return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
        }

    }

}


