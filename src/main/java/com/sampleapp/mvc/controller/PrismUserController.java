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
 * Controller for PrismUser purposes. This is a demo to showcase the following:
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
        //prismUserService.createPrismUser(signupForm.getFirstName(),signupForm.getLastName(),signupForm.getEmail());
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PrismUser getUser(@PathVariable UUID uuid) throws RecordNotFoundException {
        //For purpose of the demo we return a dummy object, else we would execute the following
        /*
        PrismUser prismUser = prismUserService.getPrismUser(uuid);
        if(prismUser == null) {
            throw new RecordNotFoundException("Record not found for user with uuid: " + uuid);
        }
        */

        PrismUser prismUser = new PrismUser();
        prismUser.setFirstName("john");
        prismUser.setLastName(("doe"));
        prismUser.setEmail("john@doe.com");
        return prismUser;
    }
}