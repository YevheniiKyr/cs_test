package com.example.cs_test.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalDateArguments extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public IllegalDateArguments(String message) {
        super(message);
    }

}
