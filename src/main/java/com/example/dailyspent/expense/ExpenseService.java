package com.example.dailyspent.expense;

import com.example.dailyspent.exceptions.UserNotFoundException;
import com.example.dailyspent.expense.dto.ExpenseDTO;
import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserService userService;

    public ExpenseModel saveExpense(ExpenseDTO expenseDTO, Long userId) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);

        ExpenseModel expense = new ExpenseModel(expenseDTO);
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<ExpenseModel> getExpensesByUser(Long userId) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
        return expenseRepository.findAllByUserUserId(userId);
    }
}
