package com.example.microblog.springDataRest;

import com.example.microblog.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="users", path="users")
public interface RepoUser extends CrudRepository<User, Long> {

}
