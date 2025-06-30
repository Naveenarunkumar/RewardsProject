package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 *
 * This class handles specific custom exceptions and maps them
 * to appropriate HTTP responses.
 */

public class GlobalExceptionHandler {
    /**
     * @param ex the thrown InvalidTransactionException
     * @return ResponseEntity with error message and HTTP 400 status
     */

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<String> handleInvalidTransaction(InvalidTransactionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
