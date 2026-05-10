package com.bryanvillafuerte.moneytracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.Transaction;
import com.bryanvillafuerte.moneytracker.domain.TransactionType;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> filterTransactions(TransactionType type, Category category, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.findAll().stream()
            .filter(transaction -> type == null || transaction.type().equals(type))
            .filter(transaction -> category == null || transaction.category().equals(category))
            .filter(transaction -> (fromDate == null || transaction.date().isAfter(fromDate)) && (toDate == null || transaction.date().isBefore(toDate)))
            .collect(Collectors.toList());
    }
}
