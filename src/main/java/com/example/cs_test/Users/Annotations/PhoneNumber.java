package com.example.cs_test.Users.Annotations;


import com.example.cs_test.Users.Validations.PhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "Invalid phone number. Pattern should be  + {12numbers}. e.g: +38090990990 or {10numbers}. e.g: 0503516123";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

