package com.example.dailyspent.expense.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaveExpenseDTO(
        @NotNull(message = "Amount can't be null")
        BigDecimal amount,
        @NotNull(message = "Category can't be null")
        String category
) {
}
