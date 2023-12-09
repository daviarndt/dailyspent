package com.example.dailyspent.user.dto;

import com.example.dailyspent.phone.dto.SavePhoneDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record SaveUserDTO(
        @NotBlank(message = "First Name is mandatory")
        String firstName,
        @NotBlank(message = "Last Name is mandatory")
        String lastName,
        @Email
        String email,
        List<SavePhoneDTO> phoneNumbers
) {
        public SaveUserDTO {
                if (phoneNumbers == null) {
                        phoneNumbers = new ArrayList<>();
                }
        }
}
