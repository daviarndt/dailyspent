package com.example.dailyspent.user;

import com.example.dailyspent.utils.exceptions.UserAlreadyExistsException;
import com.example.dailyspent.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel saveUser(UserModel user, String method) {
        System.out.println("AQUI TÁ O MÉTODO: " + method);
        if (!method.equals("PUT")) {
            if (userExistsByEmail(user.getEmail())) {
                throw new UserAlreadyExistsException();
            }
        }
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserModel deactivateUser(Long userId, String method) {
        UserModel user = getUserById(userId).orElseThrow(UserNotFoundException::new);
            user.setActive(false);
            saveUser(user, method);
            return user;
    }

    public UserModel activateUser(Long userId, String method) {
        UserModel user = getUserById(userId).orElseThrow(UserNotFoundException::new);
        user.setActive(true);
        saveUser(user, method);
        return user;
    }

    public boolean userExistsByEmail(String email) {
        Optional<UserModel> user = userRepository.findByEmail(email);
        return user.isPresent() ? true : false;
    }
}
