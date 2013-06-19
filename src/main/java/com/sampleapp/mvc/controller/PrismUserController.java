package com.sampleapp.mvc.controller;

import com.sampleapp.mvc.exception.BadRequestException;
import com.sampleapp.mvc.exception.RecordNotFoundException;
import com.sampleapp.mvc.model.PrismUser;
import com.sampleapp.mvc.model.SignupForm;
import com.sampleapp.service.PrismUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Controller for PrismUser purposes.
 */
@Controller
@RequestMapping("/prismUser")
public class PrismUserController {

    @Autowired
    private PrismUserService prismUserService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void createUser(@Valid SignupForm signupForm,
                                                   BindingResult bindingResult) throws BadRequestException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        prismUserService.createPrismUser(signupForm.getFirstName(),signupForm.getLastName(),signupForm.getEmail());
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PrismUser getUser(@PathVariable UUID uuid) throws RecordNotFoundException {

        PrismUser prismUser = prismUserService.getPrismUser(uuid);
        if(prismUser == null) {
            throw new RecordNotFoundException("Record not found for user with uuid: " + uuid);
        }
        return prismUser;
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deleteUser(@PathVariable UUID uuid) throws RecordNotFoundException {
        // In a typical Spring/Web application, we would get uuid of the prism user from session/security context
        prismUserService.deletePrismUser(uuid);
    }
}