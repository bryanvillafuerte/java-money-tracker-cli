package com.bryanvillafuerte.moneytracker.cli;

import com.bryanvillafuerte.moneytracker.repository.InMemoryTransactionRepository;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;
import com.bryanvillafuerte.moneytracker.service.TransactionService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTrackerCliTest {

    @Test
    void noArgumentsPrintUsage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{});

        assertEquals("Usage: list" + System.lineSeparator(), output.toString());
    }

    @Test
    void listWithNoTransactionsPrintsMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MoneyTrackerCli cli = createCli(output);

        cli.run(new String[]{"list"});

        assertEquals("No transactions found." + System.lineSeparator(), output.toString());
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