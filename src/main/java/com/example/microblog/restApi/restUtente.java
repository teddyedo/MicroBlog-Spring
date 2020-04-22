package com.example.microblog.restApi;

import java.util.List;
import java.util.Optional;

import com.example.microblog.entities.Utente;
import com.example.microblog.repository.UserRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
     * Get the list of all users
     * @return the list of all users
     */

    @ApiOperation("View a list of all the users")
    @GetMapping
    public ResponseEntity<List<Utente>> getUsers() {

        List<Utente> list = repoU.findAll();
        return new ResponseEntity<List<Utente>>(list, HttpStatus.OK);
    }

    /**
     * Find a user by ID
     * @param id User Id
     * @return the User with the Id given, if it exists
     */
    @ApiOperation("View the User with the given ID") 
    @GetMapping(value = "{id}")
    public ResponseEntity<Utente> getUser(@ApiParam(value = "The id of the User that will be returned")
                                          @PathVariable("id") String id) {
        Optional<Utente> op = repoU.findById(Long.parseLong(id));
        if (op.isPresent()) {
            Utente u = op.get();
            u.add(linkTo(methodOn(restUtente.class).getUser(id)).withSelfRel());
            u.add(linkTo(methodOn(restUtente.class).getUsers()).withRel("users"));
            u.add(linkTo(methodOn(restPost.class).getPostsByUserId(id)).withRel("posts"));
            return new ResponseEntity<Utente>(u, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a new user
     * @param user JSON formatted user
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
     * Modify the user with the given ID
     * @param id user id
     * @param utente JSON formatted user with changes
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Modify the User with the given ID") 
    @PutMapping(value = "{id}")
    public ResponseEntity modifyUser(@ApiParam(value = "The id of the User that will be modified")
                                     @PathVariable("id") String id,
                                     @ApiParam(value = "The User with the new information")
                                     @RequestBody Utente utente) {

        Optional<Utente> op = repoU.findById(Long.parseLong(id));

        if (! op.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            repoU.save(utente);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * Delete the user with the given Id
     * @param id user id
     * @return Http response, bad request, created or conflict
     */

    @ApiOperation("Delete the User with the given ID")
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteUser(@ApiParam(value = "The id of the User that will be deleted")
                                     @PathVariable("id") String id) {

        Optional<Utente> op = repoU.findById(Long.parseLong(id));

        if (!op.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            repoU.deleteById(Long.parseLong(id));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}