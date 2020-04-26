package com.example.microblog.restApi;

import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.microblog.entities.Commento;
import com.example.microblog.repository.CommentoRepository;

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
 * 
 * restCommento
 * 
 * @author Allari Edoardo
 * 
 */

@Api("Basic operations on Comments")
@RequestMapping("Microblog/api/comments")
@RestController
public class restCommento {

    @Autowired
    CommentoRepository repoC;

    /**
     * Get the list of all comments
     * @return the list of all comments
     */

    @ApiOperation("View the list of all the Comments")
    @GetMapping
    public ResponseEntity<List<Commento>> getComments() {

        List<Commento> list = repoC.findAll();
        for (Commento c : list){
            c.add(linkTo(methodOn(restCommento.class).getCommento(String.valueOf(c.getId()))).withSelfRel());
            c.add(linkTo(methodOn(restCommento.class).getComments()).withRel("comments"));
            c.add(linkTo(methodOn(restUtente.class).getUser(String.valueOf(c.getUtente().getId()))).withRel("user"));
            c.add(linkTo(methodOn(restPost.class).getPost(String.valueOf(c.getPost().getId()))).withRel("post"));
        }
        return new ResponseEntity<List<Commento>>(list, HttpStatus.OK);
    }

    /**
     * Find a comment by Id
     * @param id Comment Id
     * @return the comment with the Id given, if it exists
     */

    @ApiOperation("View the Comment with the given ID") 
    @GetMapping(value = "{id}")
    public ResponseEntity<Commento> getCommento(@ApiParam(value = "The id of the Comment that wil be returned")
                                                          @PathVariable("id") String id) {
        Optional<Commento> op = repoC.findById(Long.parseLong(id));
        if (op.isPresent()) {
            Commento c = op.get();
            c.add(linkTo(methodOn(restCommento.class).getCommento(id)).withSelfRel());
            c.add(linkTo(methodOn(restCommento.class).getComments()).withRel("comments"));
            c.add(linkTo(methodOn(restUtente.class).getUser(String.valueOf(c.getUtente().getId()))).withRel("user"));
            c.add(linkTo(methodOn(restPost.class).getPost(String.valueOf(c.getPost().getId()))).withRel("post"));
            return new ResponseEntity<Commento>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a new comment
     * @param commento JSON formatted comment
     * @return Http response, created or bad request
     */

    @ApiOperation("Create a new Comment") 
    @PostMapping
    public ResponseEntity createComment(@ApiParam(value = "The Comment that will be created")
                                         @RequestBody Commento commento) {

        if (commento == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            repoC.save(commento);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Modify the comment with the given ID
     * @param id comment id
     * @param Commento JSON formatted comment with changes
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Modify the comment with the given ID") 
    @PutMapping(value = "{id}")
    public ResponseEntity modifyComment(@ApiParam(value = "The id of the Comment that will be modified")
                                         @PathVariable("id") String id,
                                         @ApiParam(value = "The Comment with the new information")
                                         @RequestBody Commento Commento) {

        Optional<Commento> op = repoC.findById(Long.parseLong(id));

        if (!op.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else{
            repoC.save(Commento);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * Delete the comment with the given Id
     * @param id comment id
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Delete the Comment with the given ID")
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCommento(@ApiParam(value = "The id of the Comment that will be deleted")
                                         @PathVariable("id") String id) {

        Optional<Commento> op = repoC.findById(Long.parseLong(id));
        
        if (!op.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else {
            repoC.deleteById(Long.parseLong(id));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}