package org.striuil.expensetrackerjava.service;

import org.striuil.expensetrackerjava.entity.Expense;

import java.util.List;

public interface ExpenseService {
    //create
    Expense saveExpense(Expense expense);

    //read
    List<Expense> fetchExpenseList();

    //update
    Expense updateExpense(Expense expense, Long expenseId);

    //delete
    void deleteExpenseById(Long expenseId);
}
