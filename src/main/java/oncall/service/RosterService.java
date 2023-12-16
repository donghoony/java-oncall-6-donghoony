package oncall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import oncall.domain.CombinedRoster;
import oncall.domain.DailyRoster;
import oncall.domain.MonthlyCalendar;
import oncall.domain.MonthlyRoster;
import oncall.domain.RosterDay;
import oncall.domain.Worker;

public class RosterService {
    private final MonthlyCalendar monthlyCalendar;
    private Worker previousWorker;

    public RosterService(MonthlyCalendar monthlyCalendar) {
        this.monthlyCalendar = monthlyCalendar;
    }

    public MonthlyRoster makeMonthlyRoster(CombinedRoster combinedRoster) {
        List<DailyRoster> roasters = new ArrayList<>();

        for (RosterDay rosterDay : getRosterDays()) {
            Worker worker = getWorker(combinedRoster, rosterDay);
            roasters.add(new DailyRoster(rosterDay, worker));
        }

        return new MonthlyRoster(roasters);
    }

    private Worker getWorker(CombinedRoster combinedRoster, RosterDay rosterDay) {
        Worker worker = combinedRoster.getWorker(rosterDay, previousWorker);
        previousWorker = worker;
        return worker;
    }

    private List<RosterDay> getRosterDays() {
        return IntStream.range(1, monthlyCalendar.getLastDay())
                .mapToObj(monthlyCalendar::get)
                .toList();
    }

}
