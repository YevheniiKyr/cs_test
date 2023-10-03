package com.example.cs_test.Users.Validations;

import com.example.cs_test.Users.Annotations.ConditionalValidation;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

//Now it may be easier without ConditionalValidation
//but i believe this wrapper may be useful in the future
@Component
public class ConditionalValidator implements ConstraintValidator<ConditionalValidation, Object> {
    private Class<? extends Annotation> validationAnnotation;

    @Override
    public void initialize(ConditionalValidation annotation) {
        validationAnnotation = annotation.applicableValidation();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (validationAnnotation != null) {
            try {
                // Get the annotation's validator class
                Class<?> validationClass = validationAnnotation.getAnnotation(Constraint.class).validatedBy()[0];
                // Create an instance of the validator class
                ConstraintValidator<Annotation, Object> validator = (ConstraintValidator<Annotation, Object>) validationClass.getDeclaredConstructor().newInstance();
                // Initialize the validator with the annotation parameters
                validator.initialize(null);
                // Validate the value using the validator
                return validator.isValid(value, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
