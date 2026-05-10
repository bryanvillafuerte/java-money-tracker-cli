package com.bryanvillafuerte.moneytracker.cli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.bryanvillafuerte.moneytracker.repository.InMemoryTransactionRepository;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;
import com.bryanvillafuerte.moneytracker.service.TransactionService;

class MoneyTrackerCliTest {

    @Test
    void noArgumentsPrintUsage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{});

        String expectedOutput = "Usage: list" + System.lineSeparator() +
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