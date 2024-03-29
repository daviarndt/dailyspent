package com.example.dailyspent.expense;

import com.example.dailyspent.expense.dto.DescribeExpenseDTO;
import com.example.dailyspent.user.dto.DescribeUserDTO;
import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import com.example.dailyspent.expense.dto.SaveExpenseDTO;
import com.example.dailyspent.utils.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("expense")
@SecurityRequirement(name = "bearer-key")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @Transactional
    @PostMapping()
    public ResponseEntity<ApiResponse<DescribeExpenseDTO>> saveExpense(@Valid @RequestBody SaveExpenseDTO saveExpenseDTO, Principal principal) {
        DescribeExpenseDTO expense = expenseService.saveExpense(saveExpenseDTO, principal.getName());
        return new ResponseEntity<>(ApiResponse.success(expense), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Page<DescribeExpenseDTO>>> getExpenses(@PageableDefault(size = 25) Pageable pageable, Principal principal) {
        Page<DescribeExpenseDTO> expenses = expenseService.getExpensesByUser(pageable, principal.getName());
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(ApiResponse.noContent(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ApiResponse.success(expenses), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{expenseId}")
    public ResponseEntity<ApiResponse<DescribeUserDTO>> deleteExpenseById(@PathVariable Long expenseId, Principal principal) {
        if (expenseId.toString().isBlank() || expenseId <= 0) {
            throw new IdIsIllegalException();
        }
        DescribeUserDTO describeUserDTO = expenseService.deleteExpenseById(expenseId, principal.getName());
        return new ResponseEntity<>(ApiResponse.success(describeUserDTO), HttpStatus.OK);
    }
}
