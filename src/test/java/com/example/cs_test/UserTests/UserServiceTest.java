package com.example.cs_test.UserTests;

import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import com.example.cs_test.Users.DTO.PatchDTO;
import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.Mappers.UserMapper;
import com.example.cs_test.Users.UserModel;
import com.example.cs_test.Users.UserRepository;
import com.example.cs_test.Users.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    private static final String NUMBER = "1234567890";
    private static final String fullName = "john doe";

    private static UserModel sampleUser() {
        UserModel sampleUser = new UserModel();
        sampleUser.setId(1L);
        sampleUser.setFirstName("john");
        sampleUser.setLastName("doe");
        return sampleUser;
    }

    private static RequestDTO userDTO() {
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
    public void testFindUserById() {
        UserModel sampleUser = sampleUser();

        // Test when a user with ID 1 exists
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        UserModel foundUser = userService.getUserById(1L);
        Assertions.assertEquals(1L, foundUser.getId().longValue());
        Assertions.assertEquals(fullName, foundUser.getFirstName() + " " + foundUser.getLastName());
        //Test when user with ID=2 doesn`t exist
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(2L);
        });
    }

    @Test
    public void testFindUsersInBirthdateRange() {
        UserModel inRange = sampleUser();

        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.NOVEMBER, 22);
        Date inRangeDate = calendar.getTime();
        inRange.setBirthDate(inRangeDate);

        List<UserModel> usersInRange = new ArrayList<>();
        usersInRange.add(inRange);

        Date from = calendar.getTime();
        calendar.set(2020, Calendar.NOVEMBER, 22);
        Date to = calendar.getTime();

        when(userRepository.findByBirthDateBetween(from, to)).thenReturn(usersInRange);
        //Test with valid params (to < from)
        List<UserModel> usersInRangeCheck = userService.getInDateRange(from, to);
        Assertions.assertEquals(1, usersInRangeCheck.size());
        //Test with wrong params (to < from)
        assertThrows(IllegalDateArguments.class, () -> {
            userService.getInDateRange(to, from);
        });
    }

    @Test
    public void testSaveUsers() {
        UserModel user = sampleUser();
        when(userRepository.save(user)).thenReturn(user);
        UserModel savedUser = userService.saveUser(user);
        Assertions.assertEquals(fullName, savedUser.getFirstName() + " " + savedUser.getLastName());
    }

    @Test
    public void testDeleteUser() {
        UserModel sampleUser = sampleUser();
        // Test when a user with ID 1 exists - success
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        userService.deleteUserByID(1L);
        // Test when a user with ID 2 not exists - ResourceNotFoundException
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUserByID(2L);
        });
    }

    @Test
    public void testUpdateUser() {
        RequestDTO userDTO = userDTO();
        UserModel user = userMapper.toUser(userDTO);
        when(userRepository.save(user)).thenReturn(user);
        // Test when a user with ID 1 exists - success
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserModel updatedUser = userService.updateUser(userDTO, 1L);
        Assertions.assertEquals(updatedUser.getPhoneNumber(), NUMBER);
        // Test when a user with ID 2 does not exist - ResourceNotFoundException
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(userDTO, 2L);
        });
    }

    @Test
    public void testUpdateUserPartial() {
        UserModel user = sampleUser();
        PatchDTO patchDTO = new PatchDTO();
        final String newName = "PATCHED_NAME";
        patchDTO.setFirstName(newName);

        when(userRepository.save(user)).thenReturn(user);
        // Test when a user with ID 1 exists - success
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserModel updatedUser = userService.updateUserPartial(patchDTO, 1L);
        Assertions.assertEquals(updatedUser.getFirstName(), newName);
        // Test when a user with ID 2 not exists - ResourceNotFoundException
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUserPartial(patchDTO, 2L);
        });
    }


}
