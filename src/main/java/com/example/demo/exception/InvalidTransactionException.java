package com.example.demo.exception;

/**
 * Custom exception thrown when a transaction is invalid.
 * 
 * Typically used to handle cases such as:
 * - Negative transaction amounts
 */

public class InvalidTransactionException extends RuntimeException {
    /**
     * Constructs a new InvalidTransactionException with a specific error message.
     *
     * @param message the detail message explaining the reason for the exception
     * 
     */
	    public InvalidTransactionException(String message) {
        super(message);
    }
}
