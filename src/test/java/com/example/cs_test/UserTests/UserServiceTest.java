package com.example.cs_test.UserTests;

import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import com.example.cs_test.Users.User;
import com.example.cs_test.Users.UserRepository;
import com.example.cs_test.Users.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Before
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    private static User sampleUser(){
        User sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setFirstName("john");
        sampleUser.setLastName("doe");
        return sampleUser;
    }

    @Test
    public void testFindUserById() {
        User sampleUser = sampleUser();
        // Mock the repository's behavior
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Test when a user with ID 1 exists
        User foundUser = userService.getUserById(1L).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        //Test when user with ID=1 exists
        assertEquals(1L, foundUser.getId().longValue());
        assertEquals("john doe", foundUser.getFirstName() + " " + foundUser.getLastName());
        //Test when user with ID=2 doesn`t exist
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(2L).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        });
    }

    @Test
    public void testFindUsersInBirthdateRange() {
        User inRange =  sampleUser();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.NOVEMBER, 22);
        Date inRangeDate = calendar.getTime();
        inRange.setBirthDate(inRangeDate);
        List<User> usersInRange = new ArrayList<>();
        usersInRange.add(inRange);

        Date from = calendar.getTime();
        calendar.set(2020, Calendar.NOVEMBER, 22);
        Date to = calendar.getTime();
        when(userRepository.findByBirthDateBetween(from, to)).thenReturn(usersInRange);
        //Test with valid params (to < from)
        List<User> usersInRangeCheck = userService.getInDateRange(from, to);
        assertEquals(1, usersInRangeCheck.size());
        //Test with wrong params (to < from)
        assertThrows(IllegalDateArguments.class, () -> {
            userService.getInDateRange(to, from);
        });
    }

    @Test
    public void testSaveUsers() {
        User user = sampleUser();
        System.out.println(user.getBirthDate());
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals("john doe", savedUser.getFirstName() + " " + savedUser.getLastName());

    }

    @Test
    public void testDeleteUser() {
        User sampleUser = sampleUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        // Test when a user with ID 1 exists - success
        userService.deleteUserByID(1L);
        // Test when a user with ID 2 not exists - ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUserByID(2L);
        });
    }


}
