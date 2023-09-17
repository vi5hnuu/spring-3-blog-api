package com.vi5hnu.blogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiException extends RuntimeException{
    final private HttpStatusCode httpStatusCode;
    public ApiException(String message, HttpStatusCode httpStatusCode){
        super(message);
        this.httpStatusCode=httpStatusCode;
    }
    public HttpStatusCode statusCode(){
        return this.httpStatusCode;
    }
}
