package oncall.io;

import oncall.domain.MonthlyRoster;

public interface OnCallOutput {
    void printAskingMonthAndWeekDay();

    void printAskingWeekDayWorkers();

    void printAskingHolidayWorkers();

    void printRoster(MonthlyRoster monthlyRoster);
}
