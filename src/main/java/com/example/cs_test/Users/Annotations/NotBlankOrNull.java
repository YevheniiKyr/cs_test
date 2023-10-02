package com.example.cs_test.Users.Annotations;

import com.example.cs_test.Users.Validations.NotBlankOrNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = NotBlankOrNullValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface NotBlankOrNull {
    String message() default "{org.hibernate.validator.constraints.NotBlankOrNull.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
