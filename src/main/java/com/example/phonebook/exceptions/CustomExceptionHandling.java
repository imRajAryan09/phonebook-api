package com.example.phonebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidContactException.class)
    public ResponseEntity<Object> handleExceptions(InvalidContactException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        response.setStatus("404");
        response.setError("Not Found");
        response.setPath(webRequest.getDescription(false).split("=")[1]);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleExceptions(BadRequestException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        response.setStatus("400");
        response.setError("Bad Request");
        response.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
