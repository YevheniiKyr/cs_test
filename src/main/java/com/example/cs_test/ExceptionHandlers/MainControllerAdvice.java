package com.example.cs_test.ExceptionHandlers;

import com.example.cs_test.Exceptions.ApiException;
import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import com.example.cs_test.Exceptions.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class MainControllerAdvice {

    //custom exceptions handling
    @ExceptionHandler(value = {IllegalDateArguments.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleIllegalDateArguments(IllegalDateArguments ex) {
        HttpStatus badRequest =  HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException,  badRequest);
    }

    //validation errors handling
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getFieldErrors().forEach(fieldError -> {
            String error = fieldError.getField() + " : " + fieldError.getRejectedValue() + ". " + fieldError.getDefaultMessage();
            errors.add(error);
        });
        ValidationError response = new ValidationError(
                "Invalid values for:",
                errors,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //deserialization errors
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonException(HttpMessageNotReadableException ex) {
        String errorMessage = "Invalid JSON format: "  +  "Invalid date format. Use yyyy-MM-dd.'";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    //internal server errors
    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleOtherExceptions(Exception ex) {
            return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


}


