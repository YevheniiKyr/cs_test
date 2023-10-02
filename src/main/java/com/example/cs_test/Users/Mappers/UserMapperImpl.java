package com.example.cs_test.Users.Mappers;

import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.DTO.ResponseDTO;
import com.example.cs_test.Users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(RequestDTO requestDTO) {
        User user = new User();
        user.setAddress(requestDTO.getAddress());
        user.setBirthDate(requestDTO.getBirthDate());
        user.setEmail(requestDTO.getEmail());
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        return  user;
    }

    @Override
    public ResponseDTO toDTO(User user) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setBirthDate(user.getBirthDate());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setAddress(user.getAddress());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setPhoneNumber(user.getPhoneNumber());
        return responseDTO;
    }
}
