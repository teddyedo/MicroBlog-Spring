package com.example.microblog.springDataRest;

import com.example.microblog.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="comments", path="comments")
public interface RepoComment extends CrudRepository<Comment, Long> {

}
