package com.example.cs_test.Users;

import com.example.cs_test.Users.DTO.PatchDTO;
import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.DTO.ResponseDTO;
import com.example.cs_test.Users.Mappers.UserMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class Users {

    private final UserService userService;
    private final UserMapperImpl userMapper;

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getUsersInDateRange(
            @RequestParam(name = "from") Date fromDate,
            @RequestParam(name = "to") Date toDate
    ) {
        List<UserModel> users = userService.getInDateRange(fromDate, toDate);
        List<ResponseDTO> usersDTO = users.stream().map(userMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid RequestDTO requestDTO) {
        UserModel user = userMapper.toUser(requestDTO);
        UserModel savedUser = userService.saveUser(user);
        ResponseDTO savedUserDTO = userMapper.toDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateUserPartial(
            @RequestBody @Valid PatchDTO patchDTO,
            @PathVariable Long id
    ) {
        UserModel updatedUser = userService.updateUserPartial(patchDTO, id);
        ResponseDTO updatedUserDTO = userMapper.toDTO(updatedUser);

        return ResponseEntity.ok(updatedUserDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateUser(
            @RequestBody @Valid RequestDTO updateDTO,
            @PathVariable Long id
    )  {
        UserModel savedUser = userService.updateUser(updateDTO, id);
        ResponseDTO savedUserDTO = userMapper.toDTO(savedUser);
        return ResponseEntity.ok(savedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserByID(id);
        return ResponseEntity.ok().build();
    }

}
