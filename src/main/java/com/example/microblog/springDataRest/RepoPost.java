package com.example.microblog.springDataRest;

import com.example.microblog.entities.Post;
import com.example.microblog.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="posts", path="posts")
public interface RepoPost extends PagingAndSortingRepository<Post, Long> {

    List<Post> findByUser(User u);
    List<Post> findPostsByUser_Id(Long id);


}
