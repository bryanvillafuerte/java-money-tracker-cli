package com.bryanvillafuerte.moneytracker.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void createTransaction() {
        UUID id = UUID.randomUUID();
        String description = "Test Transaction";
        BigDecimal amount = new BigDecimal("100.00");
        LocalDate date = LocalDate.of(2023, 1, 1);
        TransactionType type = TransactionType.EXPENSE;
        Category category = Category.FOOD;

        Transaction transaction = new Transaction(id, description, amount, date, type, category);

        assertEquals(id, transaction.id());
        assertEquals(description, transaction.description());
        assertEquals(amount, transaction.amount());
        assertEquals(date, transaction.date());
        assertEquals(type, transaction.type());
        assertEquals(category, transaction.category());
    }

}