package com.bryanvillafuerte.moneytracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.MonthlySummary;
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

    public List<MonthlySummary> getMonthlySummaries() {
        List<Transaction> transactions = transactionRepository.findAll();

        Map<YearMonth, List<Transaction>> transactionsByMonth = transactions.stream()
            .collect(Collectors.groupingBy(transaction -> YearMonth.from(transaction.date())));

        return transactionsByMonth.entrySet().stream()
            .map(entry -> {
                YearMonth month = entry.getKey();
                List<Transaction> monthTransactions = entry.getValue();

                BigDecimal totalIncome = monthTransactions.stream()
                    .filter(transaction -> transaction.type().equals(TransactionType.INCOME))
                    .map(Transaction::amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal totalExpenses = monthTransactions.stream()
                    .filter(transaction -> transaction.type().equals(TransactionType.EXPENSE))
                    .map(Transaction::amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal netBalance = totalIncome.subtract(totalExpenses);

                return new MonthlySummary(month, totalIncome, totalExpenses, netBalance);
            })
            .collect(Collectors.toList());
    }

    public List<Transaction> filterTransactions(TransactionType type, Category category, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.findAll().stream()
            .filter(transaction -> type == null || transaction.type().equals(type))
            .filter(transaction -> category == null || transaction.category().equals(category))
            .filter(transaction -> (fromDate == null || transaction.date().isAfter(fromDate)) && (toDate == null || transaction.date().isBefore(toDate)))
            .collect(Collectors.toList());
    }
}
