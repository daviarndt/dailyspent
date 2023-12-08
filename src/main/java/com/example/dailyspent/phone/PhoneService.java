package com.example.dailyspent.phone;

import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import com.example.dailyspent.utils.exceptions.PhoneNotFoundException;
import com.example.dailyspent.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UserService userService;

    public UserModel savePhone(PhoneModel phone, Long userId) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
        phone.setUser(user);
        phoneRepository.save(phone);
        return user;
    }

    public Optional<PhoneModel> getPhoneById(Long phoneId) {
        return phoneRepository.findById(phoneId);
    }

    public UserModel deletePhoneById(Long phoneId) {
        PhoneModel phone = getPhoneById(phoneId).orElseThrow(PhoneNotFoundException::new);
        UserModel user = userService.getUserById(phone.getUser().getUserId()).orElseThrow(UserNotFoundException::new);
        phoneRepository.deleteById(phoneId);
        return user;
    }
}
