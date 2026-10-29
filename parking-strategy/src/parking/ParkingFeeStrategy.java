package parking;

import java.time.Duration;

public interface ParkingFeeStrategy {

        long calculateFee(Duration parkedTime);

        String tariffName();
}
