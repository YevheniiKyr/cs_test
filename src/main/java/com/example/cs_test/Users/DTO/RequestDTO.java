package com.example.cs_test.Users.DTO;

import com.example.cs_test.Users.Annotations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

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
//    @ConditionalValidation(applicableValidation = NotEmpty.class)
    @NotBlankOrNull
    private String address;
    @ConditionalValidation(applicableValidation = PhoneNumber.class)
    private String phoneNumber;

}

