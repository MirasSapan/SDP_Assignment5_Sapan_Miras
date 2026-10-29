package parking;

import java.time.Duration;

public class MonthlySubscriptionStrategy implements ParkingFeeStrategy {

    @Override
    public long calculateFee(Duration parkedTime) {
        return 0;
    }

    @Override
    public String tariffName() {
        return "Monthly subscription";
    }
}
