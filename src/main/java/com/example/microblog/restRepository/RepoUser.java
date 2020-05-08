package com.example.microblog.restRepository;

import com.example.microblog.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@Api(tags = "User Entity")
@RepositoryRestResource(collectionResourceRel="users", path="users")

public interface RepoUser extends CrudRepository<User, Long> {

    @ApiOperation("Find a user by username")
    Optional<User> findByUsername(@ApiParam(value = "The username of the user") String username);

}
