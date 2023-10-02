package com.example.cs_test.Users.Validations;

import com.example.cs_test.Users.Annotations.NotBlankOrNull;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class NotBlankOrNullValidator implements ConstraintValidator<NotBlankOrNull, String> {

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return s.trim().length() > 0;
    }

    @Override
    public void initialize(NotBlankOrNull constraint) {

    }
}
