package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import com.example.dailyspent.utils.ApiResponse;
import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    UserService userService;

    @Transactional
    @PostMapping(value = "/{userId}")
    public ResponseEntity<ApiResponse<UserModel>> savePhone(@Valid @RequestBody PhoneModel phone, @PathVariable Long userId) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        UserModel user = phoneService.savePhone(phone, userId);
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{phoneId}")
    public ResponseEntity<ApiResponse<UserModel>> deletePhoneById(@PathVariable Long phoneId) {
        if (phoneId.toString().isBlank() && phoneId <= 0) {
            throw new IdIsIllegalException();
        }
        UserModel user = phoneService.deletePhoneById(phoneId);
        return new ResponseEntity<>(ApiResponse.success(user), HttpStatus.OK);
    }
}
