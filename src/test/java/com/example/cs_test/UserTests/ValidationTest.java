package com.example.cs_test.UserTests;

import com.example.cs_test.Users.Annotations.ConditionalValidation;
import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.Validations.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ValidationTest {
    @Mock
    private ConstraintValidatorContext context;
    @Autowired
    private DateValidator dateValidator;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private PhoneNumberValidator phoneNumberValidator;
    @Autowired
    private NotBlankOrNullValidator notBlankOrNullValidator;


    @Test
    public void testIsPersonEighteenOrOlder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.NOVEMBER, 22);
        Date validDateOfBirth = calendar.getTime();
        boolean isValid = dateValidator.isPersonEighteenOrOlder(validDateOfBirth);
        Assertions.assertTrue(isValid);

        calendar.set(2010, Calendar.NOVEMBER, 22);
        Date notValidDateOfBirth = calendar.getTime();
        isValid = dateValidator.isPersonEighteenOrOlder(notValidDateOfBirth);
        assertFalse(isValid);
    }

    @Test
    public void testIfEmailIsValid() {
        String validEmail = "example@gmail.com";
        boolean isValid = emailValidator.isValid(validEmail, context);
        Assertions.assertTrue(isValid);

        String[] invalidEmails ={
                "example-gmail.com",
                "example-gmail.com",
                "@gmail.com",
                "example@",
                "example@gmailcom"
        };

        for(String invalidEmail: invalidEmails){
            isValid = emailValidator.isValid(invalidEmail, context);
            assertFalse(isValid);
        }
    }

    @Test
    public void testIfNumberIsValid() {
        boolean isValid;
        String [] validNumbers = {
                // long form: + and 12 digits
                "+380909767123",
                //short form: 10 digits
                "0505055005"
        };
        for(String validNumber: validNumbers){
            isValid = phoneNumberValidator.isValid(validNumber, context);
            Assertions.assertTrue(isValid);
        }

        String [] invalidNumbers = {
                // without +
                "380909767123",
                //11 digits
                "+38090976712",
                //14 digits
               "+38090976712111",
        };
        for(String invalidNumber: invalidNumbers){
            isValid = phoneNumberValidator.isValid(invalidNumber, context);
            assertFalse(isValid);
        }
    }

    @Test
    public void testNotBlankOrNull() {
        boolean isValid;
        String [] validStrings = {
                null,
                //notBlank
                "not blank"
        };
        for(String validString: validStrings){
            isValid = notBlankOrNullValidator.isValid(validString, context);
            Assertions.assertTrue(isValid);
        }

        String [] invalidStrings = {
                "",
                "        ",
                " "
        };
        for(String invalidString: invalidStrings){
            isValid = notBlankOrNullValidator.isValid(invalidString, context);
            assertFalse(isValid);
        }
    }

    @Test
    public void testConditionalValidator() throws NoSuchFieldException {
        // check number conditional validation
        boolean isValid;
        RequestDTO myInstance = new RequestDTO();
        Field numberField = myInstance.getClass().getDeclaredField("phoneNumber");
        ConditionalValidation conditionalValidation = numberField.getAnnotation(ConditionalValidation.class);
        ConditionalValidator phoneNumberConditionalValidator = new ConditionalValidator();
        phoneNumberConditionalValidator.initialize(conditionalValidation);
        String [] validNumbers = {
                // long form: + and 12 digits
                "+380909767123",
                //short form: 10 digits
                "0505055005",
                //null also valid in conditional
                null
        };

        for(String validNumber: validNumbers){
            isValid = phoneNumberConditionalValidator.isValid(validNumber, context);
            Assertions.assertTrue(isValid);
        }

        String [] invalidNumbers = {
                // without +
                "380909767123",
                //11 digits
                "+38090976712",
                //14 digits
                "+38090976712111",
        };
        for(String invalidNumber: invalidNumbers){
            isValid = phoneNumberConditionalValidator.isValid(invalidNumber, context);
            assertFalse(isValid);
        }
    }
}
