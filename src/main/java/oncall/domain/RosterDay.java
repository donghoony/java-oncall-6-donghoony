package oncall.domain;

import java.time.DayOfWeek;
import java.time.MonthDay;
import oncall.config.HolidayConfig;

public class RosterDay {
    private final MonthDay monthDay;
    private final DayOfWeek dayOfWeek;

    public RosterDay(MonthDay monthDay, DayOfWeek dayOfWeek) {
        this.monthDay = monthDay;
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isHoliday() {
        return HolidayConfig.holidays.contains(monthDay) || isWeekend();
    }

    private boolean isWeekend() {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isHolidayAndWeekDay() {
        return isHoliday() && !isWeekend();
    }
}
