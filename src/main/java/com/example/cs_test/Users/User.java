package com.example.cs_test.Users;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "User")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the date format here (e.g., "yyyy-MM-dd")
    private Date birthDate;
    @Column
    private String address;
    @Column
    private String phoneNumber;


}
