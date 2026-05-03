package com.bryanvillafuerte.moneytracker.service;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.Transaction;
import com.bryanvillafuerte.moneytracker.domain.TransactionType;
import com.bryanvillafuerte.moneytracker.repository.InMemoryTransactionRepository;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionServiceTest {

    @Test
    void addTransactionStoresTransactionInRepository() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction = createTransaction();

        transactionService.addTransaction(transaction);

        assertEquals(List.of(transaction), transactionRepository.findAll());
    }

    @Test
    void listAllTransactionsReturnsSavedTransactions() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction = createTransaction();

        transactionRepository.save(transaction);

        assertEquals(List.of(transaction), transactionService.listAllTransactions());
    }

    private static Transaction createTransaction() {
        return new Transaction(
                UUID.randomUUID(),
                "Test Transaction",
                new BigDecimal("100.00"),
                LocalDate.of(2023, 1, 1),
                TransactionType.EXPENSE,
                Category.FOOD
        );
    }
}