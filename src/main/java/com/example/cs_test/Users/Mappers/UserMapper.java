package com.example.cs_test.Users.Mappers;

import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.DTO.ResponseDTO;
import com.example.cs_test.Users.UserModel;

public interface UserMapper {
    UserModel toUser(RequestDTO requestDTO);
    ResponseDTO toDTO(UserModel user);
}
