package com.example.dailyspent.expense;

import com.example.dailyspent.exceptions.UserNotFoundException;
import com.example.dailyspent.expense.dto.ExpenseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<ExpenseModel>> getExpensesByUser(@PathVariable Long userId) {
        try {
            if (!userId.toString().isBlank() && userId > 0) {
                List<ExpenseModel> expenses = expenseService.getExpensesByUser(userId);
                if (expenses.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(expenses, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
