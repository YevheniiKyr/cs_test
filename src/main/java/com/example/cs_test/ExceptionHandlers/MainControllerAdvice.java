package com.example.cs_test.ExceptionHandlers;

import com.example.cs_test.Exceptions.ApiException;
import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@RestControllerAdvice
public class MainControllerAdvice {

    @ExceptionHandler(value = {IllegalDateArguments.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleIllegalDateArguments(IllegalDateArguments ex) {
        HttpStatus badRequest =  HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,  badRequest);
    }

    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleOtherExceptions(Exception ex) {
            return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


}


