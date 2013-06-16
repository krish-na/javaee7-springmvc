package com.sampleapp.mvc.controller;

import com.sampleapp.mvc.exception.BadRequestException;
import com.sampleapp.mvc.exception.RecordNotFoundException;
import com.sampleapp.mvc.model.InputFieldError;
import com.sampleapp.mvc.model.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 *  ControllerAdvice annotation brings global handle using @ExceptionHandler, which applies to all classes that are
 *  annotated with @Controller annotation. Multiple exceptions can be handled in an exception handler method.
 *
 *  http://static.springsource.org/spring-framework/docs/3.2.0.M2/reference/html/mvc.html#mvc-exceptionhandlers
 * */

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResultResponse handleBadRequestException(BadRequestException ex) {
        List<InputFieldError> inputFieldErrors = getInputFieldErrors(ex.getResult().getFieldErrors());
        return new ResultResponse(HttpStatus.BAD_REQUEST,inputFieldErrors);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ResultResponse handleRecordNotFoundException(RecordNotFoundException ex) {
        return new ResultResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Extract field name and default message from BindingResult's FieldErrors object. We only need
     * these fields to be returned to the client.
     *
     * @param fieldErrors   List of field errors from BindingResult
     * @return      List of InputFieldErrors which contain field name and a default message
     */
    public List<InputFieldError> getInputFieldErrors(List<FieldError> fieldErrors) {
        if(fieldErrors == null || fieldErrors.size() < 1) return null;
        List<InputFieldError> inputFieldErrors = new ArrayList<InputFieldError>();
        for(FieldError fieldError: fieldErrors) {
            inputFieldErrors.add(new InputFieldError(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return inputFieldErrors;
    }
}
