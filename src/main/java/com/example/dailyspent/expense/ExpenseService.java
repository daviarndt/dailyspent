package com.example.dailyspent.expense;

import com.example.dailyspent.utils.exceptions.UserNotFoundException;
import com.example.dailyspent.expense.dto.ExpenseDTO;
import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<ExpenseModel> getExpensesByUser(Long userId, Pageable pageable) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
        return expenseRepository.findAllByUserUserId(userId, pageable);
    }
}
