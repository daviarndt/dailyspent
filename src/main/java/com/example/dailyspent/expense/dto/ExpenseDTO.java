package com.example.dailyspent.expense.dto;

import java.math.BigDecimal;

public record ExpenseDTO(
        BigDecimal amount,
        String category
) {
}
