package com.example.dailyspent.expense;

import com.example.dailyspent.expense.dto.DescribeExpenseDTO;
import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import com.example.dailyspent.expense.dto.SaveExpenseDTO;
import com.example.dailyspent.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @Transactional
    @PostMapping(value = "/{userId}")
    public ResponseEntity<ApiResponse<DescribeExpenseDTO>> saveExpense(@Valid @RequestBody SaveExpenseDTO saveExpenseDTO, @PathVariable Long userId) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        DescribeExpenseDTO expense = expenseService.saveExpense(saveExpenseDTO, userId);
        return new ResponseEntity<>(ApiResponse.success(expense), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<ApiResponse<Page<DescribeExpenseDTO>>> getExpensesByUser(@PathVariable Long userId, @PageableDefault(size = 25) Pageable pageable) {
        if (userId.toString().isBlank() && userId <= 0) {
            throw new IdIsIllegalException();
        }
        Page<DescribeExpenseDTO> expenses = expenseService.getExpensesByUser(userId, pageable);
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(ApiResponse.noContent(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ApiResponse.success(expenses), HttpStatus.OK);
    }
}
