package com.example.dailyspent.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping(value = "/saveUser")
    public ResponseEntity<UserModel> saveUser(@Valid @RequestBody UserModel user) {
        try {
            UserModel savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(value = "/deactivateUser/{userId}")
    public ResponseEntity<UserModel> deactivateUser(@PathVariable Long userId) {
        try {
            if (!userId.toString().isBlank()) {
                Optional<UserModel> userOptional = userService.deactivateUser(userId);
                if (userOptional.isPresent()) {
                    return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
