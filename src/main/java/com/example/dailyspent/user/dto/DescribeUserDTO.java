package com.example.dailyspent.user.dto;

import com.example.dailyspent.expense.dto.DescribeExpenseDTO;
import com.example.dailyspent.phone.PhoneModel;
import com.example.dailyspent.phone.dto.DescribePhoneDTO;
import com.example.dailyspent.user.UserModel;

import java.util.List;

public record DescribeUserDTO(
        Long userId,
        String firstName,
        String lastName,
        String email,
        boolean isActive,
        List<DescribePhoneDTO> phoneNumbers,
        List<DescribeExpenseDTO> expenses
) {
    public DescribeUserDTO(UserModel userModel) {
        this(
                userModel.getUserId(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                userModel.isActive(),
                userModel.getPhoneNumbers().stream().map(DescribePhoneDTO::new).toList(),
                userModel.getExpenses().stream().map(DescribeExpenseDTO::new).toList()
        );
    }
}
