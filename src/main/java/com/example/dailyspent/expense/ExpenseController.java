package com.example.dailyspent.expense;

import com.example.dailyspent.utils.exceptions.IdIsIllegalException;
import com.example.dailyspent.utils.exceptions.UserNotFoundException;
import com.example.dailyspent.expense.dto.ExpenseDTO;
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
    @PostMapping(value = "/saveExpense/{userId}")
    public ResponseEntity<ExpenseModel> saveExpense(@Valid @RequestBody ExpenseDTO expenseDTO, @PathVariable Long userId) {
        try {
            if (!userId.toString().isBlank() && userId > 0) {
                ExpenseModel expense = expenseService.saveExpense(expenseDTO, userId);
                return new ResponseEntity<>(expense, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getExpenses/{userId}")
    public ResponseEntity<ApiResponse<Page<ExpenseModel>>> getExpensesByUser(@PathVariable Long userId, @PageableDefault(size = 25) Pageable pageable) {
        try {
            if (!userId.toString().isBlank() && userId > 0) {
                Page<ExpenseModel> expenses = expenseService.getExpensesByUser(userId, pageable);
                if (expenses.isEmpty()) {
                    return new ResponseEntity<>(ApiResponse.noContent(), HttpStatus.OK);
                }
                return new ResponseEntity<>(ApiResponse.success(expenses), HttpStatus.OK);
            }
            throw new IdIsIllegalException();
        }
        catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>(ApiResponse.exception(userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
        }
        catch (IdIsIllegalException idIsIllegalException) {
            return new ResponseEntity<>(ApiResponse.exception(idIsIllegalException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
