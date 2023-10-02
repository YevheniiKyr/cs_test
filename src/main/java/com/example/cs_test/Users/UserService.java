package com.example.cs_test.Users;

import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import com.example.cs_test.Users.DTO.PatchDTO;
import com.example.cs_test.Users.DTO.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getInDateRange(Date from, Date to) {
        if (to.before(from)) {
            throw new IllegalDateArguments(" 'to' need be after ' from' ");
        }
        return userRepository.findByBirthDateBetween(from, to);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserByID(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        userRepository.deleteById(id);
    }


    public User updateUserPartial(PatchDTO patchDTO, Long id) {
        User user = getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        if (patchDTO.getEmail() != null) {
            user.setEmail(patchDTO.getEmail());
        }
        if (patchDTO.getFirstName() != null) {
            user.setFirstName(patchDTO.getFirstName());
        }
        if (patchDTO.getLastName() != null) {
            user.setLastName(patchDTO.getLastName());
        }
        if (patchDTO.getBirthDate() != null) {
            user.setBirthDate(patchDTO.getBirthDate());
        }
        if (patchDTO.getAddress() != null) {
            user.setAddress(patchDTO.getAddress());
        }
        if (patchDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(patchDTO.getPhoneNumber());
        }
        return saveUser(user);
    }

    public User updateUser(RequestDTO updateDTO, Long id) {
        User existingUser = getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        existingUser.setEmail(updateDTO.getEmail());
        existingUser.setFirstName(updateDTO.getFirstName());
        existingUser.setLastName(updateDTO.getLastName());
        existingUser.setBirthDate(updateDTO.getBirthDate());
        existingUser.setAddress(updateDTO.getAddress());
        existingUser.setPhoneNumber(updateDTO.getPhoneNumber());

        return saveUser(existingUser);
    }
}
