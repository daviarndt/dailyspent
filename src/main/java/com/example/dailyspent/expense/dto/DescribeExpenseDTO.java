package com.example.dailyspent.expense.dto;

import com.example.dailyspent.expense.ExpenseModel;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DescribeExpenseDTO(
        Long expenseId,
        BigDecimal amount,
        String category,
        LocalDateTime createdDate,
        Long userId
) {
    public DescribeExpenseDTO(ExpenseModel expenseModel) {
        this(
                expenseModel.getExpenseId(),
                expenseModel.getAmount(),
                expenseModel.getCategory(),
                expenseModel.getCreatedDate(),
                expenseModel.getUser().getUserId()
        );
    }
}
