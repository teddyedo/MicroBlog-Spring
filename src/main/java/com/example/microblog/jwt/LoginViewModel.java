package com.example.microblog.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Allari Edoardo
 * Login model
 */

public class LoginViewModel {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

}
