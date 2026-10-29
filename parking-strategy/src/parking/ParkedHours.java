package parking;

import java.time.Duration;

final class ParkedHours {

    private ParkedHours() {
    }

        static long startedHours(Duration parkedTime) {
        long minutes = parkedTime.toMinutes();
        long fullHours = minutes / 60;
        boolean hasStartedHour = minutes % 60 != 0;
        return hasStartedHour ? fullHours + 1 : fullHours;
    }
}
