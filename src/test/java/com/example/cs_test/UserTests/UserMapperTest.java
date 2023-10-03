package com.example.cs_test.UserTests;

import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.Mappers.UserMapper;
import com.example.cs_test.Users.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    private static final String NUMBER = "1234567890";

    private static RequestDTO userDTO () {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setEmail("example@example.com");
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");

        Date birthDate = new Date();
        requestDTO.setBirthDate(birthDate);
        requestDTO.setAddress("123 Main St");
        requestDTO.setPhoneNumber(NUMBER);
        return requestDTO;
    }
    @Test
    public void userMapperWithAllFieldsPresent(){
        RequestDTO requestDTO = userDTO();
        UserModel user = userMapper.toUser(requestDTO);
        assertEquals(user.getPhoneNumber() , NUMBER);
    }

    @Test
    public void userMapperWithoutNumber(){
        RequestDTO requestDTO = userDTO();
        requestDTO.setPhoneNumber(null);
        UserModel user = userMapper.toUser(requestDTO);
        assertNull(user.getPhoneNumber());
    }


}
