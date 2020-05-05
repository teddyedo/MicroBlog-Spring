package com.example.microblog.springDataRest;

import com.example.microblog.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="posts", path="posts")
public interface RepoPost extends CrudRepository<Post, Long> {

}
