package org.striuil.expensetrackerjava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue
    Long id;

    @NotNull(message = "Amount Field Cannot Be Empty!")
    @Positive(message = "Amount Cannot Be Negative Or Zero!")
    BigDecimal amount;

    LocalDate date;

    String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    /*
    if no date was provided manually when creating a new expense,
    the date is assigned automatically to the current date.
    executes automatically before entity is inserted into the database.
     */
    @PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
