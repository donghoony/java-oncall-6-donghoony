package oncall.controller;

import java.util.List;
import oncall.domain.CombinedRoster;
import oncall.domain.MonthlyCalendar;
import oncall.domain.MonthlyRoster;
import oncall.domain.Roster;
import oncall.domain.Worker;
import oncall.io.OnCallInput;
import oncall.io.OnCallOutput;
import oncall.service.RosterService;

public class OnCallController extends ExceptionLoopController {
    private final RosterService rosterService;
    private final OnCallInput input;
    private final OnCallOutput output;

    public OnCallController(RosterService rosterService, OnCallInput input, OnCallOutput output) {
        this.rosterService = rosterService;
        this.input = input;
        this.output = output;
    }


    public void makeRoster() {
        MonthlyCalendar monthlyCalendar = repeatUntilValid(this::makeMonthlyCalendar);

        Roster weekDayRoster = repeatUntilValid(this::makeWeekDayRoster);
        Roster holidayRoster = repeatUntilValid(this::makeHolidayRoster);
        CombinedRoster combinedRoster = new CombinedRoster(weekDayRoster, holidayRoster);

        MonthlyRoster monthlyRoster = rosterService.makeMonthlyRoster(monthlyCalendar, combinedRoster);
        output.printRoster(monthlyRoster);
    }

    private MonthlyCalendar makeMonthlyCalendar() {
        output.printAskingMonthAndWeekDay();
        return input.getMonthAndDayOfWeek();
    }

    private Roster makeWeekDayRoster() {
        output.printAskingWeekDayWorkers();
        List<Worker> weekdayWorkers = input.getWorkers();
        return new Roster(weekdayWorkers);
    }

    private Roster makeHolidayRoster() {
        output.printAskingHolidayWorkers();
        List<Worker> holidayWorkers = input.getWorkers();
        return new Roster(holidayWorkers);
    }
}
