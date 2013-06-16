package com.sampleapp.mvc.validator;

import com.sampleapp.mvc.model.SignupForm;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * SignupFormTest to validate input bean validation.
 */
public class SignupFormTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSignupForm() {
        SignupForm signupForm = new SignupForm("john","doe","johndoe@doe.com");
        System.out.println(signupForm.toString());
        Set<ConstraintViolation<SignupForm>> constraintViolations = validator.validate( signupForm );
        assertEquals(0,constraintViolations.size());
    }

    @Test
    public void testInvalidFirstName() {
        SignupForm signupForm = new SignupForm("","doe","johndoe@doe.com");
        Set<ConstraintViolation<SignupForm>> constraintViolations = validator.validate( signupForm );
        assertEquals(1,constraintViolations.size());
        assertEquals("Invalid format",constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidEmailFormat() {
        SignupForm signupForm = new SignupForm("john","doe","d.com");
        Set<ConstraintViolation<SignupForm>> constraintViolations = validator.validate( signupForm );
        assertEquals(1,constraintViolations.size());
        assertEquals("not a well-formed email address",constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testUnsafeHtmlInput() {
        SignupForm signupForm = new SignupForm("<script> alert('hello there') </script>","doe","johndoe@doe.com");
        Set<ConstraintViolation<SignupForm>> constraintViolations = validator.validate( signupForm );
        assertEquals(1,constraintViolations.size());
        assertEquals("may have unsafe html content",constraintViolations.iterator().next().getMessage());
    }
}