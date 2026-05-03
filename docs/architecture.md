# Architecture

## Current Architecture

The project is currently a plain Java Maven CLI application.

```text
MoneyTrackerApplication
└── prints startup message
```

## Target Architecture

```text
CLI layer
└── parses user commands and prints results

Service layer
└── contains business operations such as add, filter, summarize, import, and export

Repository layer
└── stores and retrieves transactions

Domain layer
└── models transactions, categories, and transaction types

IO layer
└── handles CSV and JSON import/export
```

## Package Responsibilities

- `cli`: command parsing and terminal output.
- `domain`: records, enums, and business data structures.
- `service`: application use cases and business logic.
- `repository`: transaction storage contracts and implementations.
- `io`: file import/export code.
- `exception`: custom validation and file processing exceptions.

## Design Principle

Keep command-line concerns at the edge of the application. The domain and service layers should be testable without reading from or writing to the terminal.
