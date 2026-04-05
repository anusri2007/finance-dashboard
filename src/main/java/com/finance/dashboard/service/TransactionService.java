package com.finance.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dashboard.entity.Transaction;
import com.finance.dashboard.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    // Save Transaction
    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    // Get All
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    // Get By User
    public List<Transaction> getByUser(String email) {
        return repository.findByEmail(email);
    }

    // Get By ID (ADDED)
    public Transaction getById(Long id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaction not found"));
    }

    // Update Transaction
    public Transaction update(Long id, Transaction transaction){

        Transaction existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Transaction not found"));

        existing.setTitle(transaction.getTitle());
        existing.setAmount(transaction.getAmount());
        existing.setType(transaction.getType());
        existing.setCategory(transaction.getCategory());
        existing.setEmail(transaction.getEmail());

        return repository.save(existing);
    }

    // Delete
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Total Income
    public Double totalIncome(String email){
        Double income = repository.totalIncome(email);
        return income == null ? 0.0 : income;
    }

    // Total Expense
    public Double totalExpense(String email){
        Double expense = repository.totalExpense(email);
        return expense == null ? 0.0 : expense;
    }

    // Balance
    public Double balance(String email){

        Double income = totalIncome(email);
        Double expense = totalExpense(email);

        return income - expense;
    }

    // Category Summary
    public List<Object[]> categorySummary(String email){
        return repository.categorySummary(email);
    }

    // Monthly Summary
    public List<Object[]> monthlySummary(String email){
        return repository.monthlySummary(email);
    }
}