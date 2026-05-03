package com.bryanvillafuerte.moneytracker.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Transaction(
        UUID id,
        String description,
        BigDecimal amount,
        LocalDate date,
        TransactionType type,
        Category category
) {}
