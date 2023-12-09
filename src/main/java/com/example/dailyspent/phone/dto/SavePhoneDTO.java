package com.example.dailyspent.phone.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SavePhoneDTO(
        @Size(min = 1, max = 4)
        @NotBlank(message = "Country Code is mandatory")
        String countryCode,
        @NotBlank(message = "Phone Number is mandatory")
        String phoneNumber
) {
}
