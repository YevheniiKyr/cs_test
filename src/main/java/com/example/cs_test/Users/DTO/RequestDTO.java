package com.example.cs_test.Users.DTO;

import com.example.cs_test.Users.Annotations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestDTO {

    @Email
    private String email;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @MoreThan18Years
    private Date birthDate;
    @NotBlankOrNull
    private String address;
    @ConditionalValidation(
            applicableValidation = PhoneNumber.class,
            message = "Invalid phone number. Pattern should be  + {12numbers}. e.g: +38090990990 or {10numbers}. e.g: 0503516123"
    )
    private String phoneNumber;

}

