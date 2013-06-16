package com.sampleapp.mvc.model;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Model object used for JSON response purposes.
 */
public class ResultResponse {

    private HttpStatus status;

    private String message;

    private List<InputFieldError> inputFieldErrors;

    public ResultResponse(HttpStatus status, List<InputFieldError> inputFieldErrors) {
        this.status = status;
        this.inputFieldErrors = inputFieldErrors;
    }

    public ResultResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<InputFieldError> getInputFieldErrors() {
        return inputFieldErrors;
    }

    public void setInputFieldErrors(List<InputFieldError> inputFieldErrors) {
        this.inputFieldErrors = inputFieldErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
