package com.bryanvillafuerte.moneytracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.bryanvillafuerte.moneytracker.domain.Category;
import com.bryanvillafuerte.moneytracker.domain.MonthlySummary;
import com.bryanvillafuerte.moneytracker.domain.Transaction;
import com.bryanvillafuerte.moneytracker.domain.TransactionType;
import com.bryanvillafuerte.moneytracker.repository.InMemoryTransactionRepository;
import com.bryanvillafuerte.moneytracker.repository.TransactionRepository;

class TransactionServiceTest {
    
    private static final String DESCRIPTION = "Test Transaction";
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");
    private static final LocalDate DATE = LocalDate.of(2023, 1, 1);
    private static final TransactionType INCOME = TransactionType.INCOME;
    private static final TransactionType EXPENSE = TransactionType.EXPENSE;
    private static final Category FOOD = Category.FOOD;
    private static final Category SALARY = Category.SALARY;
    

    @Test
    void addTransactionStoresTransactionInRepository() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction = createTransaction(DESCRIPTION, AMOUNT, DATE, INCOME, SALARY);

        transactionService.addTransaction(transaction);

        assertEquals(List.of(transaction), transactionRepository.findAll());
    }

    @Test
    void listAllTransactionsReturnsSavedTransactions() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction = createTransaction(DESCRIPTION, AMOUNT, DATE, INCOME, SALARY);

        transactionRepository.save(transaction);

        assertEquals(List.of(transaction), transactionService.listAllTransactions());
    }

    @Test
    void filterByTypeReturnsOnlyMatchingTransactions() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction1 = createTransaction(DESCRIPTION, AMOUNT, DATE, INCOME, SALARY);
        Transaction transaction2 = createTransaction(DESCRIPTION, AMOUNT, DATE, EXPENSE, FOOD);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        assertEquals(List.of(transaction2), transactionService.filterTransactions(EXPENSE, null, null, null));
    }

    @Test
    void filterByCategoryReturnsOnlyMatchingTransactions() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction1 = createTransaction(DESCRIPTION, AMOUNT, DATE, INCOME, SALARY);
        Transaction transaction2 = createTransaction(DESCRIPTION, AMOUNT, DATE, EXPENSE, FOOD);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        assertEquals(List.of(transaction1), transactionService.filterTransactions(null, SALARY, null, null));
    }

    @Test
    void filterWithNullParametersReturnsAllTransactions() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction transaction1 = createTransaction(DESCRIPTION, AMOUNT, DATE, INCOME, SALARY);
        Transaction transaction2 = createTransaction(DESCRIPTION, AMOUNT, DATE, EXPENSE, FOOD);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        assertEquals(List.of(transaction1, transaction2), transactionService.filterTransactions(null, null, null, null));
    }

    @Test
    void getMonthlySummariesGroupsTransactionsByMonth() {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        Transaction mayTransaction1 = createTransaction(DESCRIPTION, AMOUNT, LocalDate.of(2023, 5, 1), INCOME, SALARY);
        Transaction mayTransaction2 = createTransaction(DESCRIPTION, AMOUNT, LocalDate.of(2023, 5, 2), EXPENSE, FOOD);
        Transaction aprilTransaction1 = createTransaction(DESCRIPTION, AMOUNT, LocalDate.of(2023, 4, 1), INCOME, SALARY);

        transactionRepository.save(mayTransaction1);
        transactionRepository.save(mayTransaction2);
        transactionRepository.save(aprilTransaction1);

        List<MonthlySummary> monthlySummaries = transactionService.getMonthlySummaries();

        assertEquals(2, monthlySummaries.size());
        
        MonthlySummary maySummary = monthlySummaries.stream()
            .filter(summary -> summary.month().equals(YearMonth.of(2023, 5)))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("May summary not found"));

        assertEquals(new BigDecimal("100.00"), maySummary.totalIncome());
        assertEquals(new BigDecimal("100.00"), maySummary.totalExpenses());
        assertEquals(new BigDecimal("0.00"), maySummary.netBalance());
    }

    private static Transaction createTransaction(String description, BigDecimal amount, LocalDate date, TransactionType type, Category category) {
        return new Transaction(
                UUID.randomUUID(),
                description,
                amount,
                date,
                type,
                category
        );
    }
}