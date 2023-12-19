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

        CombinedRoster combinedRoster = repeatUntilValid(this::makeCombinedRoster);
        MonthlyRoster monthlyRoster = rosterService.makeMonthlyRoster(monthlyCalendar, combinedRoster);
        output.printRoster(monthlyRoster);
    }

    private MonthlyCalendar makeMonthlyCalendar() {
        output.printAskingMonthAndWeekDay();
        return input.getMonthAndDayOfWeek();
    }

    private CombinedRoster makeCombinedRoster() {
        Roster weekDayRoster = makeWeekDayRoster();
        Roster holidayRoster = makeHolidayRoster();
        return new CombinedRoster(weekDayRoster, holidayRoster);
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
