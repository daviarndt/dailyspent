package com.example.dailyspent.user;

import com.example.dailyspent.phone.PhoneModel;
import com.example.dailyspent.phone.dto.SavePhoneDTO;
import com.example.dailyspent.user.dto.DescribeUserDTO;
import com.example.dailyspent.user.dto.SaveUserDTO;
import com.example.dailyspent.utils.exceptions.UserAlreadyExistsException;
import com.example.dailyspent.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public DescribeUserDTO saveUser(SaveUserDTO saveUserDTO) {
        if (userExistsByEmail(saveUserDTO.email())) {
            throw new UserAlreadyExistsException();
        }
        UserModel userModel = new UserModel(saveUserDTO);
        if (!saveUserDTO.phoneNumbers().isEmpty()) {
            List<PhoneModel> phones = new ArrayList<>();
            for (SavePhoneDTO phoneDTO : saveUserDTO.phoneNumbers()) {
                PhoneModel phoneModel = new PhoneModel(phoneDTO);
                phoneModel.setUser(userModel);
                phones.add(phoneModel);
            }
            userModel.setPhoneNumbers(phones);
        }
        return new DescribeUserDTO(userRepository.save(userModel));
    }

    public Optional<UserModel> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public DescribeUserDTO deactivateUser(Long userId) {
        UserModel user = getUserById(userId).orElseThrow(UserNotFoundException::new);
            user.setActive(false);
            return new DescribeUserDTO(userRepository.save(user));
    }

    public DescribeUserDTO activateUser(Long userId) {
        UserModel user = getUserById(userId).orElseThrow(UserNotFoundException::new);
        user.setActive(true);
        return new DescribeUserDTO(userRepository.save(user));
    }

    public boolean userExistsByEmail(String email) {
        Optional<UserModel> user = userRepository.findByEmail(email);
        return user.isPresent() ? true : false;
    }
}
