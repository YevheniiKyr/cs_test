package com.example.cs_test.Users.Mappers;

import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.DTO.ResponseDTO;
import com.example.cs_test.Users.User;
import org.springframework.http.ResponseEntity;

public interface UserMapper {

    User toUser(RequestDTO requestDTO);
    ResponseDTO toDTO(User user);
}
