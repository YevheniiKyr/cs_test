package com.example.cs_test.Users.Validations;

import com.example.cs_test.Users.Annotations.PhoneNumber;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String LONG_PHONE_NUMBER_PATTERN = "\\+\\d{12}";
    private static final String SHORT_PHONE_NUMBER_PATTERN = "\\d{10}";

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) return false;
        return number.matches(LONG_PHONE_NUMBER_PATTERN) || number.matches(SHORT_PHONE_NUMBER_PATTERN);

    }
}
