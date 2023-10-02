package com.example.cs_test.Users.Annotations;

import com.example.cs_test.Users.Validations.ConditionalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConditionalValidator.class)
public @interface ConditionalValidation {
    String message() default "Validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Annotation> applicableValidation();

}
