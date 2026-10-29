package parking;

import java.time.Duration;
import java.util.Objects;

public class ParkingMeter {

    private ParkingFeeStrategy tariff;

    public ParkingMeter(ParkingFeeStrategy tariff) {
        changeTariff(tariff);
    }

    public void changeTariff(ParkingFeeStrategy newTariff) {
        this.tariff = Objects.requireNonNull(newTariff, "tariff must not be null");
    }

    public long calculateFee(Duration parkedTime) {
        return tariff.calculateFee(parkedTime);
    }

    public String currentTariffName() {
        return tariff.tariffName();
    }
}
