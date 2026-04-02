package com.finance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finance.dashboard.entity.Transaction;

public interface TransactionRepository 
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByEmail(String email);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type='INCOME' AND t.email=?1")
    Double totalIncome(String email);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type='EXPENSE' AND t.email=?1")
    Double totalExpense(String email);

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.email=?1 GROUP BY t.category")
    List<Object[]> categorySummary(String email);

    @Query("SELECT MONTH(t.date), SUM(t.amount) FROM Transaction t WHERE t.email=?1 GROUP BY MONTH(t.date)")
    List<Object[]> monthlySummary(String email);
}