package com.example.cs_test;

import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            System.out.println("ERROR WHILE DATA PARSE");
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.", e);
        }
        catch (Exception e) {
            System.out.println("ERROR WHILE DATA PARSE EXCEPTION");
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.", e);
        }
    }
}
