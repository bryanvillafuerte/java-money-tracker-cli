package com.bryanvillafuerte.moneytracker.cli;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.Transaction;
import com.bryanvillafuerte.moneytracker.domain.TransactionType;
import com.bryanvillafuerte.moneytracker.service.TransactionService;

public class MoneyTrackerCli {

    private final TransactionService transactionService;
    private final PrintStream out;

    public MoneyTrackerCli(TransactionService transactionService, PrintStream out) {
        this.transactionService = transactionService;
        this.out = out;
    }

    public void run(String[] args) {
        if (args.length == 0) {
            out.println("Usage: list");
            out.println("Usage: add <description> <amount> <date> <type> <category>");
            return;
        }

        switch (args[0]) {
            case "add" -> addTransaction(args);
            case "list" -> listTransactions();
            default -> out.println("Unknown command: " + args[0]);
        }
    }

    private void addTransaction(String[] args) {
        out.println("Adding transaction...");
        transactionService.addTransaction(new Transaction(
            UUID.randomUUID(),
            args[1],
            new BigDecimal(args[2]),
            LocalDate.parse(args[3]),
            TransactionType.valueOf(args[4]),
            Category.valueOf(args[5])
        ));
        out.println("Transaction added: " + args[1]);
    }

    private void listTransactions() {
        List<Transaction> transactions = transactionService.listAllTransactions();

        if (transactions.isEmpty()) {
            out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            out.println(transaction);
        }
    }
}
