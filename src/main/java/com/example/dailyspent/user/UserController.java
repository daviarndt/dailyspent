package com.example.dailyspent.user;

import com.example.dailyspent.phone.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void saveUser(@RequestBody UserModel user) {
        userRepository.save(user);
    }

    @PostMapping(value = "/userWithPhone")
    public void saveUserWithPhone(@RequestBody UserModel user) {
        if (user.getPhoneNumbers() != null) {
            for (PhoneModel phone : user.getPhoneNumbers()) {
                phone.setUser(user);
            }
        }

        userRepository.save(user);
    }
}
