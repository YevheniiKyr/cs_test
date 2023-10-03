package com.example.cs_test.Users.Validations;

import com.example.cs_test.Users.Annotations.MoreThan18Years;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class DateValidator implements ConstraintValidator<MoreThan18Years, Date> {
    private final int userAgeLimit;

    public boolean isPersonEighteenOrOlder(Date dateOfBirth) {
        Date currentDate = new Date();
        long difference_In_Time = currentDate.getTime() - dateOfBirth.getTime();
        long difference_In_Years = (difference_In_Time / (1000L * 60 * 60 * 24 * 365));
        return difference_In_Years >= userAgeLimit;
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        return isPersonEighteenOrOlder(date);
    }

}