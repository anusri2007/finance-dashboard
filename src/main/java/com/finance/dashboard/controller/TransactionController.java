package com.finance.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.entity.Transaction;
import com.finance.dashboard.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    // Add Transaction
    @PostMapping
    public Transaction add(
            @RequestBody Transaction transaction,
            Authentication auth) {

        transaction.setEmail(auth.getName());
        return service.save(transaction);
    }

    // Get User Transactions
    @GetMapping
    public List<Transaction> get(Authentication auth) {
        return service.getByUser(auth.getName());
    }

    // Get Transaction By ID (ADDED)
    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id){
        return service.getById(id);
    }

    // Update Transaction
    @PutMapping("/{id}")
    public Transaction update(
            @PathVariable Long id,
            @RequestBody Transaction transaction,
            Authentication auth) {

        transaction.setEmail(auth.getName());
        return service.update(id, transaction);
    }

    // Delete Transaction
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Transaction Deleted Successfully";
    }

    // Total Income
    @GetMapping("/income")
    public Double income(Authentication auth){
        return service.totalIncome(auth.getName());
    }

    // Total Expense
    @GetMapping("/expense")
    public Double expense(Authentication auth){
        return service.totalExpense(auth.getName());
    }

    // Balance
    @GetMapping("/balance")
    public Double balance(Authentication auth){
        return service.balance(auth.getName());
    }

    // Category Summary
    @GetMapping("/category")
    public List<Object[]> category(Authentication auth){
        return service.categorySummary(auth.getName());
    }

    // Monthly Summary
    @GetMapping("/monthly")
    public List<Object[]> monthly(Authentication auth){
        return service.monthlySummary(auth.getName());
    }
}