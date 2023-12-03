package com.example.dailyspent.user;

import com.example.dailyspent.phone.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel saveUserWithPhone(UserModel user) {
        if (user.getPhoneNumbers() != null) {
            for (PhoneModel phone : user.getPhoneNumbers()) {
                phone.setUser(user);
            }
        }
        return userRepository.save(user);
    }
}
