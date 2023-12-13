package com.example.dailyspent.utils.security.dto;

public record RequestAuthenticationDTO(
        String login,
        String password
) {
}
