package parking;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, ParkingFeeStrategy> tariffsByCode = availableTariffs();
        List<Duration> visits = List.of(
                Duration.ofMinutes(90),
                Duration.ofHours(5).plusMinutes(10),
                Duration.ofHours(30));

        ParkingMeter meter = new ParkingMeter(tariffsByCode.get("hourly"));

        for (Duration visit : visits) {
            System.out.println("Parked for " + format(visit));
            for (ParkingFeeStrategy tariff : tariffsByCode.values()) {
                meter.changeTariff(tariff);
                System.out.printf("  %-22s -> %,6d KZT%n",
                        meter.currentTariffName(), meter.calculateFee(visit));
            }
        }
    }

    private static Map<String, ParkingFeeStrategy> availableTariffs() {
        Map<String, ParkingFeeStrategy> tariffs = new LinkedHashMap<>();
        tariffs.put("hourly", new HourlyRateStrategy());
        tariffs.put("daily-cap", new DailyCapStrategy());
        tariffs.put("mall", new FirstHoursFreeStrategy());
        tariffs.put("subscription", new MonthlySubscriptionStrategy());
        return tariffs;
    }

    private static String format(Duration duration) {
        return duration.toHours() + " h " + duration.toMinutesPart() + " min";
    }
}
