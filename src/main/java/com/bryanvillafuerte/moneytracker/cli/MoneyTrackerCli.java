package com.bryanvillafuerte.moneytracker.cli;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.MonthlySummary;
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
            out.println("Usage: list [--type TYPE] [--category CATEGORY] [--from DATE] [--to DATE]");
            out.println("Usage: add <description> <amount> <date> <type> <category>");
            out.println("Usage: summary");
            return;
        }

        switch (args[0]) {
            case "add" -> addTransaction(args);
            case "list" -> listTransactions(args);
            case "summary" -> showMonthlySummary();
            default -> out.println("Unknown command: " + args[0]);
        }
    }

    private String getArgValue(String[] args, String flag) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(flag)) {
                return args[i + 1];
            }
        }
        return null;
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

    private void listTransactions(String[] args) {
        String type = getArgValue(args, "--type");
        String category = getArgValue(args, "--category");
        String fromArg = getArgValue(args, "--from");
        String toArg = getArgValue(args, "--to");

        TransactionType transactionType = (type != null) ? TransactionType.valueOf(type) : null;
        Category transactionCategory = (category != null) ? Category.valueOf(category) : null;
        LocalDate fromDate = (fromArg != null) ? LocalDate.parse(fromArg) : null;
        LocalDate toDate = (toArg != null) ? LocalDate.parse(toArg) : null;

        List<Transaction> transactions = transactionService.filterTransactions(transactionType, transactionCategory, fromDate, toDate);

        if (transactions.isEmpty()) {
            out.println("No transactions found.");
            return;
        }

        out.println("Listing transactions...");
        for (Transaction transaction : transactions) {
            out.println(transaction);
        }
        out.println("Transactions listed.");
    }

    private void showMonthlySummary() {
        List<MonthlySummary> monthlySummaries = transactionService.getMonthlySummaries();

        for (MonthlySummary summary : monthlySummaries) {
            out.println(summary.month() + ":");
            out.println("  Income: " + summary.totalIncome());
            out.println("  Expenses: " + summary.totalExpenses());
            out.println("  Net Balance: " + summary.netBalance());
        }
    }
}
