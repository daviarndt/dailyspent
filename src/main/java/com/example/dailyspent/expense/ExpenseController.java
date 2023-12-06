package com.example.dailyspent.expense;

import com.example.dailyspent.expense.dto.ExpenseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
            ExpenseModel expense = expenseService.saveExpense(expenseDTO, userId);
            return new ResponseEntity(expense, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
