package com.example.dailyspent.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {

    Page<ExpenseModel> findAllByUserUserId(Long id, Pageable pageable);
}
