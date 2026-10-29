package parking;

import java.time.Duration;

public class FirstHoursFreeStrategy implements ParkingFeeStrategy {

    private static final Duration FREE_PERIOD = Duration.ofHours(2);
    private static final long PRICE_PER_HOUR_AFTER_FREE_PERIOD = 400;

    @Override
    public long calculateFee(Duration parkedTime) {
        if (parkedTime.compareTo(FREE_PERIOD) <= 0) {
            return 0;
        }
        Duration chargeableTime = parkedTime.minus(FREE_PERIOD);
        return ParkedHours.startedHours(chargeableTime) * PRICE_PER_HOUR_AFTER_FREE_PERIOD;
    }

    @Override
    public String tariffName() {
        return "First 2 hours free";
    }
}
