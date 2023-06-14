package com.openai.prompt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResponseStatusException ex) {
        ApiError error = new ApiError(404, ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiError> handleDefaultError(RuntimeException ex) {
//        ApiError error = new ApiError(500, ex.getMessage());
//
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
