package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UserService userService;

    public Optional<UserModel> savePhone(PhoneModel phone, Long userId) {
        Optional<UserModel> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            phone.setUser(userOptional.get());
            phoneRepository.save(phone);
            return Optional.of(userOptional.get());
        } else {
            return Optional.empty();
        }
    }
}
