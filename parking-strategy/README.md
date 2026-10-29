# Parking Fee Calculator — Strategy Pattern (Java)

Assignment #5, Software Design Patterns.
Domain: a family of interchangeable parking tariffs selected at runtime.

## Structure

| Pattern role | Class |
|---|---|
| Strategy interface | `ParkingFeeStrategy` |
| Concrete strategies | `HourlyRateStrategy`, `DailyCapStrategy`, `FirstHoursFreeStrategy`, `MonthlySubscriptionStrategy` |
| Context | `ParkingMeter` (has `changeTariff(...)` to switch strategy at runtime) |
| Client | `Main` (creates strategies, picks and switches them) |

## Run

```bash
mkdir out
javac -d out src/parking/*.java
java -cp out parking.Main
```

## Clean Code principles applied

1. **Polymorphism instead of conditionals.** `ParkingMeter` has no `if`/`switch` on tariff type; it just calls `tariff.calculateFee(...)`.
2. **Intention-revealing names.** `HourlyRateStrategy`, `DailyCapStrategy`, `startedHours`, `PRICE_PER_HOUR` say what they do without comments.
3. **Single Responsibility.** Each strategy implements exactly one pricing algorithm; the context only delegates; `ParkedHours` only rounds time.
4. **Context is algorithm-agnostic.** `ParkingMeter` depends only on the `ParkingFeeStrategy` interface (Dependency Inversion), never on concrete classes.
5. **Open/Closed (easy extension).** A new tariff is a new class implementing the interface; no existing class changes.
6. **No magic numbers.** Prices, caps and free periods are named constants.
7. **DRY.** The "every started hour is billed" rule lives in one place, `ParkedHours.startedHours`.
8. **Fail fast.** The context rejects a `null` strategy with `Objects.requireNonNull`.

## How to add a new strategy

Create `NightDiscountStrategy implements ParkingFeeStrategy` and register it in `Main.availableTariffs()`. `ParkingMeter` and the other strategies stay untouched.
