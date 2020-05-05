package com.example.microblog.springDataRest;

import com.example.microblog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="users", path="users")
public interface RepoUser extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
