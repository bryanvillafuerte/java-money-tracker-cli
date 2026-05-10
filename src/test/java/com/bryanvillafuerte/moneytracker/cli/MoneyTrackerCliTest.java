package com.bryanvillafuerte.moneytracker.cli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.TransactionType;
import com.bryanvillafuerte.moneytracker.repository.InMemoryTransactionRepository;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;
import com.bryanvillafuerte.moneytracker.service.TransactionService;

class MoneyTrackerCliTest {

    private static final String DESCRIPTION = "Test Transaction";
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");
    private static final LocalDate DATE = LocalDate.of(2023, 1, 1);
    private static final TransactionType INCOME = TransactionType.INCOME;
    private static final TransactionType EXPENSE = TransactionType.EXPENSE;
    private static final Category SALARY = Category.SALARY;
    private static final Category FOOD = Category.FOOD;

    @Test
    void noArgumentsPrintUsage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{});

        String expectedOutput = "Usage: list [--type TYPE] [--category CATEGORY] [--from DATE] [--to DATE]" + System.lineSeparator() +
                                "Usage: add <description> <amount> <date> <type> <category>" + System.lineSeparator();
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    void listWithNoTransactionsPrintsMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{"list"});

        assertEquals("No transactions found." + System.lineSeparator(), output.toString());
    }

    @Test
    void addTransactionThenDisplayTransactionList() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{"add", "Test Transaction", "100.00", "2023-01-01", "INCOME", "SALARY"});
        output.reset();
        cli.run(new String[]{"list"});

        assertTrue(output.toString().contains("Test Transaction"));
        assertTrue(output.toString().contains("100.00"));
    }

    @Test
    void listWithTypeFilterReturnsOnlyMatchingTransactions() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{"add", DESCRIPTION, AMOUNT.toString(), DATE.toString(), INCOME.toString(), SALARY.toString()});
        output.reset();
        cli.run(new String[]{"add", DESCRIPTION, AMOUNT.toString(), DATE.toString(), EXPENSE.toString(), FOOD.toString()});
        output.reset();
        cli.run(new String[]{"list", "--type", EXPENSE.name()});

        assertTrue(output.toString().contains(EXPENSE.name()));
        assertFalse(output.toString().contains(INCOME.name()));
    }

    @Test
    void unknownCommandPrintsErrorMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{"delete"});

        assertEquals("Unknown command: delete" + System.lineSeparator(), output.toString());
    }

    private static MoneyTrackerCli createCli(ByteArrayOutputStream output) {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        PrintStream out = new PrintStream(output);

        return new MoneyTrackerCli(transactionService, out);
    }
}