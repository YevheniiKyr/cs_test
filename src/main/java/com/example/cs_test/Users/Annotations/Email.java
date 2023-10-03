package com.example.cs_test.Users.Annotations;

import com.example.cs_test.Users.Validations.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "Invalid email. Pattern should be: xxxx@yyy.ddd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
