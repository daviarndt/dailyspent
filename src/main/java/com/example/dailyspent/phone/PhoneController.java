package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @Transactional
    @PostMapping(value = "/savePhone/{userId}")
    public ResponseEntity<UserModel> savePhone(@Valid @RequestBody PhoneModel phone, @PathVariable Long userId) {
        try {
            if (userId != null) {
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
}
