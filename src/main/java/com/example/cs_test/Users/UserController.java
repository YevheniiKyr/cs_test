package com.example.cs_test.Users;

import com.example.cs_test.Exceptions.IllegalDateArguments;
import com.example.cs_test.Exceptions.ResourceNotFoundException;
import com.example.cs_test.Users.DTO.PatchDTO;
import com.example.cs_test.Users.DTO.RequestDTO;
import com.example.cs_test.Users.DTO.ResponseDTO;
import com.example.cs_test.Users.Mappers.UserMapperImpl;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapperImpl userMapper;

    @GetMapping
    public ResponseEntity<List<User>> getUsersInDateRange(
            @RequestParam(name = "from") Date fromDate,
            @RequestParam(name = "to") Date toDate
    ) {
        List<User> users = userService.getInDateRange(fromDate, toDate);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid RequestDTO requestDTO) {
        User user = userMapper.toUser(requestDTO);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUserPartial(
            @RequestBody @Valid PatchDTO patchDTO,
            @PathVariable Long id
    ) {

        User updatedUser = userService.updateUserPartial(patchDTO, id);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @RequestBody @Valid RequestDTO updateDTO,
            @PathVariable Long id
    )  {

        User savedUser = userService.updateUser(updateDTO, id);
        return ResponseEntity.ok(savedUser);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserByID(id);
        return ResponseEntity.ok().build();
    }

}
