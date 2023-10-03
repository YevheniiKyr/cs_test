package com.example.cs_test.Users.DTO;

import com.example.cs_test.Users.Annotations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PatchDTO {

    @ConditionalValidation(applicableValidation = Email.class)
    private String email;

    @NotBlankOrNull
    private String firstName;

    @NotBlankOrNull
    private String lastName;
    @ConditionalValidation(applicableValidation = MoreThan18Years.class)
    private Date birthDate;
    @NotBlankOrNull
    private String address;
    @ConditionalValidation(applicableValidation = PhoneNumber.class)
    private String phoneNumber;
}
