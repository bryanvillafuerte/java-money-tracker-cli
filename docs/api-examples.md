# CLI Examples

This file will collect command examples as the CLI grows.

## Planned Commands

```bash
# Add income
java -jar target/java-money-tracker-cli.jar add income --amount 2500.00 --category salary --date 2026-05-01 --description "May salary"

# Add expense
java -jar target/java-money-tracker-cli.jar add expense --amount 42.50 --category food --date 2026-05-02 --description "Groceries"

# List transactions
java -jar target/java-money-tracker-cli.jar list

# Filter by category
java -jar target/java-money-tracker-cli.jar list --category food

# Show monthly summary
java -jar target/java-money-tracker-cli.jar summary --month 2026-05

# Export transactions
java -jar target/java-money-tracker-cli.jar export --format csv --output samples/transactions.csv
```

These examples are placeholders until the commands are implemented.
