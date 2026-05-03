package com.bryanvillafuerte.moneytracker.cli;

import com.bryanvillafuerte.moneytracker.domain.Transaction;
import com.bryanvillafuerte.moneytracker.service.TransactionService;

import java.io.PrintStream;
import java.util.List;

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
            return;
        }

        String command = args[0];

        if (command.equals("list")) {
            listTransactions();
            return;
        }

        out.println("Unknown command: " + command);
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
