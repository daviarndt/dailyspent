package com.example.dailyspent.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {

    List<ExpenseModel> findAllByUserUserId(Long id);
}
