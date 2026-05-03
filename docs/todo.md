# Todo

## Milestones

- [x] 1. Set up the Java project and package structure.
- [x] 2. Create the transaction domain model.
- [x] 3. Add transaction categories and transaction types.
- [x] 4. Store transactions in memory.
- [ ] 5. Build CLI commands for adding and listing transactions.
- [ ] 6. Add filtering by date, type, and category.
- [ ] 7. Add monthly summary reports.
- [ ] 8. Add CSV export.
- [ ] 9. Add JSON export.
- [ ] 10. Add file import.
- [ ] 11. Add custom exceptions and validation.
- [ ] 12. Add JUnit tests.
- [ ] 13. Refactor for clean architecture.
- [ ] 14. Create README with usage examples and screenshots.

## Current Task

- [ ] Create a simple service for adding and listing transactions.
- [ ] Wire the service to the in-memory repository.
- [ ] Add service tests.
- [ ] Start CLI command design for add/list.
- [ ] Run `mvn test`.
- [ ] Review the implementation before parsing real command-line input.

## Documentation Tasks

- [ ] Fill in `README.md` as features become real.
- [ ] Keep `docs/architecture.md` updated at each refactoring checkpoint.
- [ ] Keep `docs/api-examples.md` updated when CLI commands are implemented.
- [ ] Add terminal screenshots or demos near the end of the project.

## Future Ideas

- Save transactions between CLI runs.
- Add budgets by category.
- Add recurring transactions.
- Add charts or richer summaries.
- Add a Spring Boot API version as a separate repository.
