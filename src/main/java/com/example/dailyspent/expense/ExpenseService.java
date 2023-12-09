package com.example.dailyspent.expense;

import com.example.dailyspent.expense.dto.DescribeExpenseDTO;
import com.example.dailyspent.user.dto.DescribeUserDTO;
import com.example.dailyspent.utils.exceptions.ExpenseNotFoundException;
import com.example.dailyspent.utils.exceptions.UserNotFoundException;
import com.example.dailyspent.expense.dto.SaveExpenseDTO;
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

    public DescribeExpenseDTO saveExpense(SaveExpenseDTO saveExpenseDTO, Long userId) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);

        ExpenseModel expense = new ExpenseModel(saveExpenseDTO);
        expense.setUser(user);
        expense = expenseRepository.save(expense);
        return new DescribeExpenseDTO(expense);
    }

    public Page<DescribeExpenseDTO> getExpensesByUser(Long userId, Pageable pageable) {
        UserModel user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
        return expenseRepository.findAllByUserUserId(userId, pageable).map(DescribeExpenseDTO::new);
    }

    public DescribeUserDTO deleteExpenseById(Long expenseId) {
        ExpenseModel expenseModel = expenseRepository.findById(expenseId).orElseThrow(ExpenseNotFoundException::new);
        UserModel userModel = userService.getUserById(expenseModel.getUser().getUserId()).orElseThrow(UserNotFoundException::new);
        userModel.getExpenses().remove(expenseModel);
        expenseRepository.deleteById(expenseId);
        return new DescribeUserDTO(userModel);
    }
}
