package com.bryanvillafuerte.moneytracker.domain;

import java.math.BigDecimal;
import java.time.YearMonth;

public record MonthlySummary(
    YearMonth month,
    BigDecimal totalIncome,
    BigDecimal totalExpenses,
    BigDecimal netBalance
) {}
