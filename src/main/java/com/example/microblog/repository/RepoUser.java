package com.example.microblog.repository;

import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@Api(tags = "User Entity")
@RepositoryRestResource(collectionResourceRel="users", path="users")

public interface RepoUser extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
