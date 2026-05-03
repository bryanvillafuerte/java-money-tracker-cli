package com.bryanvillafuerte.moneytracker.repository;

import com.bryanvillafuerte.moneytracker.domain.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> findAll();
}
