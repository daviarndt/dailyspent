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
    private UserRepository userRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
        try {
            UserModel savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping(value = "/userWithPhone")
    public ResponseEntity<UserModel> saveUserWithPhone(@RequestBody UserModel user) {
        try {
            if (user.getPhoneNumbers() != null) {
                for (PhoneModel phone : user.getPhoneNumbers()) {
                    phone.setUser(user);
                }
            }
            UserModel savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser , HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
