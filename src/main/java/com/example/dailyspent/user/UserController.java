package com.example.dailyspent.user;

import com.example.dailyspent.user.dto.DescribeUserDTO;
import com.example.dailyspent.user.dto.SaveUserDTO;
import com.example.dailyspent.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @PutMapping(value = "/deactivate")
    public ResponseEntity<ApiResponse<DescribeUserDTO>> deactivateUser(Principal principal) {
        DescribeUserDTO user = userService.deactivateUser(principal.getName());
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/activate")
    public ResponseEntity<ApiResponse<DescribeUserDTO>> activateUser(Principal principal) {
        DescribeUserDTO user = userService.activateUser(principal.getName());
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }
}
