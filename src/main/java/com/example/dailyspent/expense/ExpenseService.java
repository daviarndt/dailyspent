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

    public DescribeExpenseDTO saveExpense(SaveExpenseDTO saveExpenseDTO, String userEmail) {
        UserModel user = userService.getUserByEmail(userEmail);

        ExpenseModel expense = new ExpenseModel(saveExpenseDTO);
        expense.setUser(user);
        expense = expenseRepository.save(expense);
        return new DescribeExpenseDTO(expense);
    }

    public Page<DescribeExpenseDTO> getExpensesByUser(Pageable pageable, String userEmail) {
        UserModel user = userService.getUserByEmail(userEmail);
        return expenseRepository.findAllByUserUserId(user.getUserId(), pageable).map(DescribeExpenseDTO::new);
    }

    public DescribeUserDTO deleteExpenseById(Long expenseId, String userEmail) {
        UserModel userModel = userService.getUserByEmail(userEmail);
        ExpenseModel expenseModel = expenseRepository.findById(expenseId).orElseThrow(ExpenseNotFoundException::new);

        if (userModel.getUserId() != expenseModel.getUser().getUserId()) {
            throw new ExpenseNotFoundException("The expense wasn't found by the User logged");
        }

        userModel.getExpenses().remove(expenseModel);
        expenseRepository.deleteById(expenseId);
        return new DescribeUserDTO(userModel);
    }
}
