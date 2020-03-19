package com.example.microblog.restApi;

import java.util.List;
import java.util.Optional;

import com.example.microblog.entities.Commento;
import com.example.microblog.entities.Commento;
import com.example.microblog.repository.CommentoRepository;
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
 * restCommento
 * 
 * @author Allari Edoardo
 * 
 */

@RequestMapping("Microblog/api/comments")
@RestController
public class restCommento {

    @Autowired
    CommentoRepository repoC;

    /**
     * 
     * @return the list of all comments
     */

    @GetMapping
    public List<Commento> getComments() {

        return repoC.findAll();
    }

    /**
     * 
     * @param id
     * @return the comment with the Id given, if it exists
     */

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Commento>> getCommento(@PathVariable("id") long id) {

        if (repoC.findById(id) != null) {

            return new ResponseEntity<Optional<Commento>>(repoC.findById(id), HttpStatus.OK);

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
    public ResponseEntity createCommento(Commento commento) {

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

    @PutMapping(value = "{id}")
    public ResponseEntity modifyCommento(@PathVariable("id") Long id, @RequestBody Commento Commento) {

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
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCommento(@PathVariable("id") long id) {
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