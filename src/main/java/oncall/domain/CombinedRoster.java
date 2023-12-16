package oncall.domain;

public class CombinedRoster {
    private final Roster weekdayRoster;
    private final Roster holidayRoster;

    public CombinedRoster(Roster weekdayRoster, Roster holidayRoster) {
        this.weekdayRoster = weekdayRoster;
        this.holidayRoster = holidayRoster;
    }

    public Worker getWeekdayWorker(Worker yesterdayWorker) {
        if (weekdayRoster.getNextWorker().equals(yesterdayWorker)) {
            return weekdayRoster.getAfterNextWorker();
        }
        return weekdayRoster.getNextWorker();
    }

    public Worker getHolidayWorker(Worker yesterdayWorker) {
        if (holidayRoster.getNextWorker().equals(yesterdayWorker)) {
            return holidayRoster.getAfterNextWorker();
        }
        return holidayRoster.getNextWorker();
    }
}
