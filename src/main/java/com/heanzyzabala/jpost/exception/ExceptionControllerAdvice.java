package com.heanzyzabala.jpost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(PostNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
