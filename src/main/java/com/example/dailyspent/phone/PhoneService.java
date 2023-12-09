package com.example.dailyspent.phone;

import com.example.dailyspent.phone.dto.DescribePhoneDTO;
import com.example.dailyspent.phone.dto.SavePhoneDTO;
import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import com.example.dailyspent.user.dto.DescribeUserDTO;
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

    public DescribePhoneDTO savePhone(SavePhoneDTO savePhoneDTO, Long userId) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
        PhoneModel phoneModel = new PhoneModel(savePhoneDTO);
        phoneModel.setUser(user);
        return new DescribePhoneDTO(phoneRepository.save(phoneModel));
    }

    public Optional<PhoneModel> getPhoneById(Long phoneId) {
        return phoneRepository.findById(phoneId);
    }

    public DescribeUserDTO deletePhoneById(Long phoneId) {
        PhoneModel phone = getPhoneById(phoneId).orElseThrow(PhoneNotFoundException::new);
        UserModel userModel = userService.getUserById(phone.getUser().getUserId()).orElseThrow(UserNotFoundException::new);
        userModel.getPhoneNumbers().remove(phone);
        phoneRepository.deleteById(phoneId);
        DescribeUserDTO describeUserDTO = new DescribeUserDTO(userModel);
        return describeUserDTO;
    }
}
