package com.sampleapp.mvc.exception;

/**
 * Exception to be called for record not found (404 Http status code)
 */
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
