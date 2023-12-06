package com.example.dailyspent.expense;

import com.example.dailyspent.expense.dto.ExpenseDTO;
import com.example.dailyspent.user.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "expense")
@Table(name = "expense")
@EqualsAndHashCode(of = "expenseId")
public class ExpenseModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public ExpenseModel() {
    }

    public ExpenseModel(ExpenseDTO expenseDTO) {
        this.amount = expenseDTO.amount();
        this.category = expenseDTO.category();
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
