package com.example.cs_test.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private String phoneNumber;

}
