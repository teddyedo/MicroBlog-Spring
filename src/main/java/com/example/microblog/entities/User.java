package com.example.microblog.entities;


import javax.persistence.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author Allari Edoardo
 * @version 1.0.0 - 14/03/2020
 * 
 */

@Entity
@ApiIgnore
public class User extends RepresentationModel<User>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(unique = true, nullable = false)
    @Basic
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Basic
    @Getter
    @Setter
    private String password;

    @Column(unique = true, nullable = false)
    @Basic
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Basic
    @Getter
    @Setter
    private String roles;

    public User() {}

    public User(long id, String username, String password, String email, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }


}
