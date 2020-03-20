package com.example.microblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFound
 * @author Allari Edoardo
 * 
 */

 @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends Exception{

    private static final long serialVersionUID = 1L;

    public ResourceNotFound(String message) {
        super(message);
    }

    
    
}