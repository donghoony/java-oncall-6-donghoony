package oncall.config;

import java.time.MonthDay;
import java.util.Set;

public class HolidayConfig {
    public static final Set<MonthDay> holidays = Set.of(
                MonthDay.of(1, 1),
                MonthDay.of(3, 1),
                MonthDay.of(5, 5),
                MonthDay.of(6, 6),
                MonthDay.of(8, 15),
                MonthDay.of(10, 3),
                MonthDay.of(10, 9),
                MonthDay.of(12, 25)
        );

    private HolidayConfig() {
    }
}
