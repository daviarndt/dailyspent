package com.example.dailyspent.user;

import com.example.dailyspent.phone.PhoneModel;
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
    @PostMapping(value = "/saveUser")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
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
    @PostMapping(value = "/saveUserWithPhone")
    public ResponseEntity<UserModel> saveUserWithPhone(@RequestBody UserModel user) {
        try {
            UserModel savedUser = userService.saveUserWithPhone(user);
            return new ResponseEntity<>(savedUser , HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
