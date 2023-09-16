package com.vi5hnu.blogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException{
    private String message;
    public ResourceNotFoundException(String message) {
        super();
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
