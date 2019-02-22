package com.jr.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * This maps Exceptions that come to controllers to response statuses
 * Can intercept and relay/transform message too
 * Also, can insert this into tests for correct exception to status mapping @see ArticleControllerTest
 */

@RestControllerAdvice
public class BlogControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArguments(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(EntityNotFoundException e) {
        return e.getMessage();
    }
}
