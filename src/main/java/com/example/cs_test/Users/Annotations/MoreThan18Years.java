package com.example.cs_test.Users.Annotations;

import com.example.cs_test.Users.Validations.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface MoreThan18Years {
    String message() default "user age need be > 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
