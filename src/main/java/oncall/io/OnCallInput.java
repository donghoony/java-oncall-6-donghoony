package oncall.io;

import java.util.List;
import oncall.domain.MonthlyCalendar;
import oncall.domain.Worker;

public interface OnCallInput {
    MonthlyCalendar getMonthAndDayOfWeek();

    List<Worker> getWeekDayWorkers();

    List<Worker> getHolidayWorkers();
}
