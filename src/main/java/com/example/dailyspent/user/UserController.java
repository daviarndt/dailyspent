package com.example.dailyspent.user;

import com.example.dailyspent.utils.ApiResponse;
import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ApiResponse<UserModel>> saveUser(@Valid @RequestBody UserModel user, HttpServletRequest httpServletRequest) {
        UserModel savedUser = userService.saveUser(user, httpServletRequest.getMethod());
        return new ResponseEntity<>(ApiResponse.success(savedUser), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(value = "/deactivateUser/{userId}")
    public ResponseEntity<ApiResponse<UserModel>> deactivateUser(@PathVariable Long userId, HttpServletRequest httpServletRequest) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        UserModel user = userService.deactivateUser(userId, httpServletRequest.getMethod());
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/activateUser/{userId}")
    public ResponseEntity<ApiResponse<UserModel>> activateUser(@PathVariable Long userId, HttpServletRequest httpServletRequest) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        UserModel user = userService.activateUser(userId, httpServletRequest.getMethod());
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }
}
