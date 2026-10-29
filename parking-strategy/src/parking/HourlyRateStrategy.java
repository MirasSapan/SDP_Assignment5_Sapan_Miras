package parking;

import java.time.Duration;

public class HourlyRateStrategy implements ParkingFeeStrategy {

    private static final long PRICE_PER_HOUR = 500;

    @Override
    public long calculateFee(Duration parkedTime) {
        return ParkedHours.startedHours(parkedTime) * PRICE_PER_HOUR;
    }

    @Override
    public String tariffName() {
        return "Hourly rate";
    }
}
