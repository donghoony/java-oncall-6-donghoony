package oncall.domain;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import oncall.config.HolidayConfig;

public class RosterDay {
    private static final DateTimeFormatter FORMAT_PATTERN = DateTimeFormatter.ofPattern("M월 d일");

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

    @Override
    public String toString() {
        String represent = monthDay.format(FORMAT_PATTERN) + " " +
                dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREA);
        if (isHolidayAndWeekDay()) {
            represent += "(휴일)";
        }
        return represent;
    }
}
