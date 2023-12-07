package com.example.dailyspent.user;

import com.example.dailyspent.utils.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel saveUser(UserModel user) {
        if (userExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
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
            updateUser(userUpdated);
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
            updateUser(userUpdated);
            return Optional.of(userUpdated);
        } else {
            return Optional.empty();
        }
    }

    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    public boolean userExistsByEmail(String email) {
        Optional<UserModel> user = userRepository.findByEmail(email);
        return user.isPresent() ? true : false;
    }
}
