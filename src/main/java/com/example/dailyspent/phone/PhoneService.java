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

    public DescribePhoneDTO savePhone(SavePhoneDTO savePhoneDTO, String email) {
        UserModel user = userService.getUserByEmail(email);
        PhoneModel phoneModel = new PhoneModel(savePhoneDTO);
        phoneModel.setUser(user);
        return new DescribePhoneDTO(phoneRepository.save(phoneModel));
    }

    public Optional<PhoneModel> getPhoneById(Long phoneId) {
        return phoneRepository.findById(phoneId);
    }

    public DescribeUserDTO deletePhoneById(Long phoneId, String userEmail) {
        UserModel userModel = userService.getUserByEmail(userEmail);
        PhoneModel phone = getPhoneById(phoneId).orElseThrow(PhoneNotFoundException::new);

        if (userModel.getUserId() != phone.getUser().getUserId()) {
            throw new PhoneNotFoundException("The phone wasn't found by the User logged");
        }

        userModel.getPhoneNumbers().remove(phone);
        phoneRepository.deleteById(phoneId);
        return new DescribeUserDTO(userModel);
    }
}
