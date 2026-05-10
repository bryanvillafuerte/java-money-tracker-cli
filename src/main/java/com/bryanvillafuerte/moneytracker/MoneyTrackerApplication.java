package com.bryanvillafuerte.moneytracker;

import com.bryanvillafuerte.moneytracker.cli.MoneyTrackerCli;
import com.bryanvillafuerte.moneytracker.repository.InMemoryTransactionRepository;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;
import com.bryanvillafuerte.moneytracker.service.TransactionService;

public class MoneyTrackerApplication {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        MoneyTrackerCli cli = new MoneyTrackerCli(transactionService, System.out);

        cli.run(args);
    }
}