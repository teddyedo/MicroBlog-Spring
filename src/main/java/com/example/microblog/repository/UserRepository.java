package com.example.microblog.repository;

import com.example.microblog.entities.Utente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<Utente, Long> {


   Optional<Utente> findUtenteByUsername(String Username);

}

