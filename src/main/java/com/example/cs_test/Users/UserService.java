package com.example.cs_test.Users;

import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import com.example.cs_test.Users.DTO.PatchDTO;
import com.example.cs_test.Users.DTO.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserModel> getInDateRange(Date from, Date to) {
        if (to.before(from)) {
            throw new IllegalDateArguments(" 'to' need be after ' from' ");
        }
        return userRepository.findByBirthDateBetween(from, to);
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
    }

    public void deleteUserByID(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        userRepository.deleteById(id);
    }


    public UserModel updateUserPartial(PatchDTO patchDTO, Long id) {
        UserModel user = getUserById(id);
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

    public UserModel updateUser(RequestDTO updateDTO, Long id) {
        UserModel existingUser = getUserById(id);
        existingUser.setEmail(updateDTO.getEmail());
        existingUser.setFirstName(updateDTO.getFirstName());
        existingUser.setLastName(updateDTO.getLastName());
        existingUser.setBirthDate(updateDTO.getBirthDate());
        existingUser.setAddress(updateDTO.getAddress());
        existingUser.setPhoneNumber(updateDTO.getPhoneNumber());

        return saveUser(existingUser);
    }
}
