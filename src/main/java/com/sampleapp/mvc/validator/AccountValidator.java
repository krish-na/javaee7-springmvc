package com.sampleapp.mvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom AccountValidator performs the validation of a given account type.
 * Return true if account exists, so we can add to our PRISM watch!
 */
public class AccountValidator implements ConstraintValidator<Account, String> {

    /**
     * Method is called by the Bean validation provider prior to any use of
     * constraint implementation.
     */
    public void initialize(Account constraint) {}

    /**
     * Method is evaluated by Bean validation provider each time a given value is
     * validated. Returns false if the value is not valid, true otherwise.
     *
     */
    public boolean isValid(String account, ConstraintValidatorContext constraintContext) {
        if(account != null && !account.isEmpty()) {
            // Here you would invoke Google/Facebook API to validate if the account exists
            return true;
        }
        return false;
    }
}
