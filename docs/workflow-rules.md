# Workflow Rules

## Mentoring Workflow

- Build the project one milestone at a time.
- Learn the Java concept before writing the code that uses it.
- Keep each task small enough to review.
- After each task, run `mvn test`.
- Paste code, test output, or errors for review before moving on.
- Prefer understanding over speed.

## Coding Rules

- Use package `com.bryanvillafuerte.moneytracker`.
- Keep domain objects focused on business concepts.
- Use `BigDecimal` for money.
- Use `LocalDate` for dates.
- Use records for simple immutable data carriers.
- Use enums for fixed sets like transaction type and category.
- Add validation deliberately, after the basic model is working.
- Keep CLI parsing separate from business logic.
- Keep file import/export separate from business logic.

## Testing Rules

- Add tests as soon as a class has behavior worth protecting.
- Test public behavior, not private implementation details.
- Use clear test names that describe the expected behavior.
- Start with simple unit tests before integration-style tests.
- Run `mvn test` before each commit.

## Git Rules

- Commit after each meaningful milestone.
- Use conventional commit style when practical.
- Keep commits small and reviewable.
- Do not mix unrelated cleanup with feature work.

## Suggested Commit Types

- `chore`: project setup, build config, housekeeping.
- `feat`: user-facing feature or domain capability.
- `test`: test-only changes.
- `refactor`: code improvement without behavior change.
- `docs`: documentation-only changes.
- `fix`: bug fix.
