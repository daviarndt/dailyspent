package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    UserService userService;

    @Transactional
    @PostMapping(value = "/savePhone/{userId}")
    public ResponseEntity<UserModel> savePhone(@Valid @RequestBody PhoneModel phone, @PathVariable Long userId) {
        try {
            if (!userId.toString().isBlank() && userId > 0) {
                Optional<UserModel> user = phoneService.savePhone(phone, userId);
                if (user.isPresent()) {
                    return new ResponseEntity<>(user.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping(value = "/deletePhone/{phoneId}")
    public ResponseEntity<UserModel> deletePhoneById(@PathVariable Long phoneId) {
        try {
            if (!phoneId.toString().isBlank() && phoneId > 0) {
                Optional<PhoneModel> phone = phoneService.getPhoneById(phoneId);
                if (phone.isPresent()) {
                    phoneService.deletePhoneById(phoneId);
                    return new ResponseEntity<>(userService.getUserById(phone.get().getUser().getUserId()).get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
