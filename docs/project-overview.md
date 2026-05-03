# Project Overview

## Name

`java-money-tracker-cli`

## Goal

Build a command-line personal finance tracker that lets a user add income, add expenses, list transactions, filter transactions, show monthly summaries, import data, and export data to CSV or JSON.

## Portfolio Outcome

The finished project should be suitable for GitHub, interviews, and portfolio review. It should show clean Java code, meaningful tests, useful documentation, and a clear commit history.

## Core Features

- Add income transactions.
- Add expense transactions.
- List all transactions.
- Filter by category, type, and date.
- Show monthly summary reports.
- Export transactions to CSV.
- Export transactions to JSON.
- Import transactions from a file.
- Validate user input with custom exceptions.

## Technical Baseline

- Java 25 LTS.
- Maven.
- JUnit 6.
- Plain Java CLI architecture.
- No database in the first version.
- No Spring Boot for this CLI project unless the scope changes.

## Target Package Structure

```text
src/main/java/com/bryanvillafuerte/moneytracker/
├── MoneyTrackerApplication.java
├── cli/
├── domain/
├── exception/
├── io/
├── repository/
└── service/
```

## Current Status

Milestone 1 is complete. The project builds with Maven and has a basic application entry point.

Next milestone: create the initial `Transaction` domain record and its first unit test.
