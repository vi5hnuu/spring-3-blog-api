package com.vi5hnu.blogapi.exception.handler;

import com.vi5hnu.blogapi.exception.ErrorDetail;
import com.vi5hnu.blogapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Specific Exception Handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleEntityNotFoundException(ResourceNotFoundException ex,WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetail(new Date(),ex.getMessage(), webRequest.getDescription(false)));
    }

    //global Exception Handlers
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(),exception.getMessage(),webRequest.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String ,String> errorMap=new HashMap<>();
        final List<ObjectError> allErrors=ex.getBindingResult().getAllErrors();
        allErrors.forEach((error)->{
            final FieldError fieldError=(FieldError)error;
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> handleAccessdeniedException(AccessDeniedException ex,WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(),ex.getMessage(),webRequest.getDescription(false)),HttpStatus.UNAUTHORIZED);
    }
}
