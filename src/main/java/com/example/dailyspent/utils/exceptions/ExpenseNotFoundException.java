package com.example.dailyspent.utils.exceptions;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(String message) {
        super(message);
    }

    public ExpenseNotFoundException() {
        super("Expense wasn't found using the expenseId provided");
    }
}
