package com.example.cs_test.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ValidationError {
    private String message;
    private List<String> errors;
    private final ZonedDateTime timestamp;

}
