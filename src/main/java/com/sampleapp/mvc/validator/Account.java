package com.sampleapp.mvc.validator;

import org.hibernate.validator.constraints.Email;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *   Custom validator to validate an existing account type such as Google,
 *   Facebook and other accounts through the use of constraint composition
 *   using @Email and others.
 */
@Email
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = AccountValidator.class)
@Documented
@Retention(RUNTIME)
public @interface Account {
    String message() default "Account does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

