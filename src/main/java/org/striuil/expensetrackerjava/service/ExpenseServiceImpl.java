package org.striuil.expensetrackerjava.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.striuil.expensetrackerjava.entity.Category;
import org.striuil.expensetrackerjava.entity.Expense;
import org.striuil.expensetrackerjava.repository.CategoryRepository;
import org.striuil.expensetrackerjava.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> fetchExpenseList() {
        return (List<Expense>) expenseRepository.findAll();
    }

    @Override
    public Expense updateExpense(Expense expense, Long expenseId) {
        Expense expenseToUpdate = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("No expense found with id: " + expenseId));

        if (expense.getAmount() != null) {
            if (expense.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero");
            }
            expenseToUpdate.setAmount(expense.getAmount());
        }

        if (expense.getDate() != null) {
            if (expense.getDate().isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Expense date cannot be in the future");
            }
            expenseToUpdate.setDate(expense.getDate());
        }

        //description is allowed to be empty, so no check for empty string
        if (expense.getDescription() != null) {
            expenseToUpdate.setDescription(expense.getDescription());
        }

        if (expense.getCategory() != null && expense.getCategory().getId() != null) {
            Long categoryId = expense.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
            expenseToUpdate.setCategory(category);
        }

        return expenseRepository.save(expenseToUpdate);
    }

    @Override
    public void deleteExpenseById(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
