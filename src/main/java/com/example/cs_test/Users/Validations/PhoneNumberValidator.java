package com.example.cs_test.Users.Validations;

import com.example.cs_test.Users.Annotations.PhoneNumber;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String PHONE_NUMBER_PATTERN = "\\+\\d{10}";

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) return false;
        System.out.println("NUMBER" + number);
        return number.matches(PHONE_NUMBER_PATTERN);

    }
}
