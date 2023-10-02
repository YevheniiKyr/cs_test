package com.example.cs_test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesConfig {

    @Value("${user.age.limit}")
    private int userAgeLimit;

    @Bean
    public int userAgeLimit() {
        return userAgeLimit;
    }
}

