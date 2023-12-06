package com.example.dailyspent.user;

import com.example.dailyspent.phone.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<UserModel> deactivateUser(Long userId) {
        Optional<UserModel> user = getUserById(userId);
        if (user.isPresent()) {
            UserModel userUpdated = user.get();
            userUpdated.setActive(false);
            saveUser(userUpdated);
            return Optional.of(userUpdated);
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserModel> activateUser(Long userId) {
        Optional<UserModel> user = getUserById(userId);
        if (user.isPresent()) {
            UserModel userUpdated = user.get();
            userUpdated.setActive(true);
            saveUser(userUpdated);
            return Optional.of(userUpdated);
        } else {
            return Optional.empty();
        }
    }
}
