package com.bryanvillafuerte.moneytracker.repository;

import com.bryanvillafuerte.moneytracker.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return List.copyOf(transactions);
    }
}
