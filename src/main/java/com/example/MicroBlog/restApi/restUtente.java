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

/**
 * 
 * restUtente
 * 
 * @author Allari Edoardo
 * 
 */

@RequestMapping("Microblog/api/users")
@RestController
public class restUtente {

    @Autowired
    UserRepository repoU;

    /**
     * 
     * @return the list of all users
     */

    @GetMapping
    public List<Utente> getUsers() {

        return repoU.findAll();
    }

    /**
     * 
     * @param id
     * @return the User with the Id given, if it exists
     */

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Utente>> getUser(@PathVariable("id") long id) {

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

    @PostMapping
    public ResponseEntity createUser(Utente user) {

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

    @PutMapping(value = "{id}")
    public ResponseEntity modifyUser(@PathVariable("id") Long id, @RequestBody Utente utente) {

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
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long id) {
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