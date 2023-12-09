package com.example.dailyspent.user;

import com.example.dailyspent.user.dto.DescribeUserDTO;
import com.example.dailyspent.user.dto.SaveUserDTO;
import com.example.dailyspent.utils.ApiResponse;
import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping()
    public ResponseEntity<ApiResponse<DescribeUserDTO>> saveUser(@Valid @RequestBody SaveUserDTO user) {
        DescribeUserDTO savedUser = userService.saveUser(user);
        return new ResponseEntity<>(ApiResponse.success(savedUser), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(value = "/deactivate/{userId}")
    public ResponseEntity<ApiResponse<DescribeUserDTO>> deactivateUser(@PathVariable Long userId) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        DescribeUserDTO user = userService.deactivateUser(userId);
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/activate/{userId}")
    public ResponseEntity<ApiResponse<DescribeUserDTO>> activateUser(@PathVariable Long userId) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        DescribeUserDTO user = userService.activateUser(userId);
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }
}
