package parking;

import java.time.Duration;

public class DailyCapStrategy implements ParkingFeeStrategy {

    private static final long PRICE_PER_HOUR = 500;
    private static final long DAILY_CAP = 3_000;
    private static final Duration ONE_DAY = Duration.ofDays(1);

    @Override
    public long calculateFee(Duration parkedTime) {
        long fullDays = parkedTime.dividedBy(ONE_DAY);
        Duration remainder = parkedTime.minus(ONE_DAY.multipliedBy(fullDays));

        long remainderFee = ParkedHours.startedHours(remainder) * PRICE_PER_HOUR;
        return fullDays * DAILY_CAP + Math.min(remainderFee, DAILY_CAP);
    }

    @Override
    public String tariffName() {
        return "Daily cap";
    }
}
