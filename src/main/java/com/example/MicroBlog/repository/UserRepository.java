package com.example.microblog.repository;

import com.example.microblog.entities.Utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<Utente, Long> {

    @Query("from Utente where Username=?1")
    Utente findByUsername(String username);

}

