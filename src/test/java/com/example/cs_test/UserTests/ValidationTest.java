package com.example.cs_test.UserTests;

import com.example.cs_test.Users.Annotations.MoreThan18Years;
import com.example.cs_test.Users.Validations.DateValidator;
import com.example.cs_test.Users.Validations.EmailValidator;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ValidationTest {
    @Mock
    private ConstraintValidatorContext context;
    @Autowired
    private DateValidator dateValidator;
    @Autowired
    private EmailValidator emailValidator;


    @Test
    public void testIsPersonEighteenOrOlder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.NOVEMBER, 22);
        Date validDateOfBirth = calendar.getTime();
        boolean isValid = dateValidator.isPersonEighteenOrOlder(validDateOfBirth);
        assertTrue(isValid);
        
        calendar.set(2010, Calendar.NOVEMBER, 22);
        Date notValidDateOfBirth = calendar.getTime();
        isValid = dateValidator.isPersonEighteenOrOlder(notValidDateOfBirth);
        assertFalse(isValid);
    }

    @Test
    public void testIfEmailIsValid() {
        String validEmail = "example@gmail.com";
        boolean isValid = emailValidator.isValid(validEmail, context);
        assertTrue(isValid);

        String invalidEmail = "example-gmail.com";
        isValid = emailValidator.isValid(invalidEmail, context);
        assertFalse(isValid);

        invalidEmail = "example-gmail.com";
        isValid = emailValidator.isValid(invalidEmail, context);
        assertFalse(isValid);

        invalidEmail = "@gmail.com";
        isValid = emailValidator.isValid(invalidEmail, context);
        assertFalse(isValid);

        invalidEmail = "example@";
        isValid = emailValidator.isValid(invalidEmail, context);
        assertFalse(isValid);

        invalidEmail = "example@gmailcom";
        isValid = emailValidator.isValid(invalidEmail, context);
        assertFalse(isValid);
    }
}
