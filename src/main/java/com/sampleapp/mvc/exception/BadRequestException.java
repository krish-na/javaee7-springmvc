package com.sampleapp.mvc.exception;

import org.springframework.validation.BindingResult;

/**
 * Exception to be called for bad request (400 Http status code)
 */
public class BadRequestException extends Exception {

    private BindingResult bindingResult;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getResult() {
        return bindingResult;
    }
}
