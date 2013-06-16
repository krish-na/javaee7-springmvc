package com.sampleapp.mvc.model;

/**
 * Class used to display an error that occurred with a field name and default message.
 * Used for input validated purposes.
 */
public class InputFieldError {

    private String field;
    private String message;

    public InputFieldError() {};

    public InputFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
