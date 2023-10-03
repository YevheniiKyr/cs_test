package com.example.cs_test.Users.Annotations;

import com.example.cs_test.Users.Validations.ConditionalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//this class is wrapper for other validations
//if object to validate is null, ConditionalValidator will return true
//if not null, it will return validator.isValid where validator is applicableValidation validator
//so if we use  @ConditionalValidation(applicableValidation = PhoneNumber.class)
//the phone number validator will be our validator
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConditionalValidator.class)
public @interface ConditionalValidation {
    String message() default "Ivalid body";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Annotation> applicableValidation();


}
