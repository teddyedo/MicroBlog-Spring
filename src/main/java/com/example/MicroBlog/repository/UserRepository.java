package com.example.microblog.repository;

import com.example.microblog.entities.Utente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
public interface UserRepository extends CrudRepository<Utente, Long> {

    @Query("from Utente where Username=?1")
    Utente findByUsername(String username);

}

