package org.striuil.expensetrackerjava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.striuil.expensetrackerjava.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    Optional<Expense> findById(Long expenseId);

    List<Expense> findByCategoryId(Long categoryId);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Expense> findByCategoryIdAndDateBetween(Long categoryId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.date BETWEEN :start AND :end")
    BigDecimal sumAmountBetweenDates(@Param("start") LocalDate startDate, @Param("end") LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category.id = :categoryId")
    BigDecimal sumAmountByCategory(@Param("categoryId") Long categoryId);
}
