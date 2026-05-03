package com.bryanvillafuerte.moneytracker.repository;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.Transaction;
import com.bryanvillafuerte.moneytracker.domain.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepositoryTest {

    @Test
    void saveStoresTransaction() {
        InMemoryTransactionRepository repository = new InMemoryTransactionRepository();
        Transaction transaction = createTransaction();

        repository.save(transaction);

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void findAllUnmodifiableList() {
        InMemoryTransactionRepository repository = new InMemoryTransactionRepository();
        Transaction transaction = createTransaction();

        repository.save(transaction);
        List<Transaction> transactions = repository.findAll();

        assertThrows(UnsupportedOperationException.class, () -> transactions.add(transaction));
    }

    private static Transaction createTransaction() {
        return new Transaction(UUID.randomUUID(), "Test Description", new BigDecimal("100.00"), LocalDate.of(2023, 1, 1), TransactionType.INCOME, Category.SALARY);
    }
}