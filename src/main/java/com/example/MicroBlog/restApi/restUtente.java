package com.example.microblog.restApi;

import java.util.List;
import java.util.Optional;

import com.example.microblog.entities.Utente;
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
 * 
 * restUtente
 * 
 * @author Allari Edoardo
 * 
 */

@Api("Basic operations on Users")
@RequestMapping("Microblog/api/users")
@RestController
public class restUtente {

    @Autowired
    UserRepository repoU;

    /**
     * 
     * @return the list of all users
     */

    @ApiOperation("View a list of all the users")
    @GetMapping
    public List<Utente> getUsers() {

        return repoU.findAll();
    }

    /**
     * 
     * @param id
     * @return the User with the Id given, if it exists
     */

    @ApiOperation("View the User with the given ID") 
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Utente>> getUser(@ApiParam(value = "The id of the User that will be returned") @PathVariable("id") long id) {

        if (repoU.findById(id) != null) {

            return new ResponseEntity<Optional<Utente>>(repoU.findById(id), HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 
     * @param user
     * @return Http response, created or bad request
     */

    @ApiOperation("Create a new User") 
    @PostMapping
    public ResponseEntity createUser(@ApiParam(value = "The User that will be created") Utente user) {

        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            repoU.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * 
     * @param id
     * @param utente
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Modify the User with the given ID") 
    @PutMapping(value = "{id}")
    public ResponseEntity modifyUser(@ApiParam(value = "The id of the User that will be modified") @PathVariable("id") Long id, @RequestBody @ApiParam(value = "The User with the new information") Utente utente) {

        if (repoU.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else if (repoU.findById(id) != null) {
            repoU.save(utente);
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

    @ApiOperation("Delete the User with the given ID")
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteUser(@ApiParam(value = "The id of the User that will be deleted") @PathVariable("id") long id) {
        if (repoU.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        else if (repoU.findById(id) != null) {
            repoU.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}