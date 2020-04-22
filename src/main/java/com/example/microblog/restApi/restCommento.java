package com.example.microblog.restApi;

import java.util.List;
import java.util.Optional;

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
     * 
     * @return the list of all comments
     */

    @ApiOperation("View the list of all the Comments")
    @GetMapping
    public List<Commento> getComments() {

        return repoC.findAll();
    }

    /**
     * 
     * @param id
     * @return the comment with the Id given, if it exists
     */

    @ApiOperation("View the Comment with the given ID") 
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Commento>> getCommento(@ApiParam(value = "The id of the Comment that wil be returned") @PathVariable("id") String id) {

        if (repoC.findById(Long.parseLong(id)) != null) {

            return new ResponseEntity<Optional<Commento>>(repoC.findById(Long.parseLong(id)), HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 
     * @param commento
     * @return Http response, created or bad request
     */

    @ApiOperation("Create a new Comment") 
    @PostMapping
    public ResponseEntity createCommento(@ApiParam(value = "The Comment that will be created") @RequestBody Commento commento) {

        if (commento == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            repoC.save(commento);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * 
     * @param id
     * @param Commento
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Modify the comment with the given ID") 
    @PutMapping(value = "{id}")
    public ResponseEntity modifyCommento(@ApiParam(value = "The id of the Comment that will be modified") @PathVariable("id") Long id, @ApiParam(value = "The Comment with the new information") @RequestBody Commento Commento) {

        if (repoC.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else if (repoC.findById(id) != null) {
            repoC.save(Commento);
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

    @ApiOperation("Delete the Comment with the given ID")
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCommento(@ApiParam(value = "The id of the Comment that will be deleted") @PathVariable("id") long id) {
        if (repoC.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else if (repoC.findById(id) != null) {
            repoC.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}