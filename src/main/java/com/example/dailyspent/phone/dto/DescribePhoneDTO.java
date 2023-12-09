package com.example.dailyspent.phone.dto;

import com.example.dailyspent.phone.PhoneModel;

public record DescribePhoneDTO(
        Long phoneId,
        String countryCode,
        String phoneNumber,
        Long userId
) {
    public DescribePhoneDTO(PhoneModel phoneModel) {
        this(
          phoneModel.getPhoneId(),
            phoneModel.getCountryCode(),
            phoneModel.getPhoneNumber(),
            phoneModel.getUser().getUserId()
        );
    }
}
