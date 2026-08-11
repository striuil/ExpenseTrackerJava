package org.striuil.expensetrackerjava.contorller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.striuil.expensetrackerjava.entity.Expense;
import org.striuil.expensetrackerjava.repository.ExpenseRepository;
import org.striuil.expensetrackerjava.service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public Expense saveExpense(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping
    public List<Expense> getExpenseList(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (categoryId != null && startDate != null && endDate != null) {
            return expenseRepository.findByCategoryIdAndDateBetween(categoryId, startDate, endDate);
        } else if (startDate != null && endDate != null) {
            return expenseRepository.findByDateBetween(startDate, endDate);
        } else if (categoryId != null) {
            return expenseRepository.findByCategoryId(categoryId);
        }
        return expenseService.fetchExpenseList();
    }

    @GetMapping("/summary")
    public BigDecimal getSummaryAmount(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (categoryId != null) {
            return expenseRepository.sumAmountByCategory(categoryId);
        } else {
            return expenseRepository.sumAmountBetweenDates(startDate, endDate);
        }
    }

    @GetMapping("/{id}")
    public Optional<Expense> getExpenseById(@PathVariable("id") Long expenseId) {
        return expenseRepository.findById(expenseId);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@Valid @RequestBody Expense expense, @PathVariable("id") Long expenseId) {
        return expenseService.updateExpense(expense, expenseId);
    }

    @DeleteMapping("/{id}")
    public String deleteExpenseById(@PathVariable("id") Long expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return "Deleted Successfully";
    }
}
