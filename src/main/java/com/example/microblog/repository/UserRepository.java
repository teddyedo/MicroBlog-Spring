package com.example.microblog.repository;

import com.example.microblog.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {


   Optional<User> findUtenteByUsername(String Username);

}

