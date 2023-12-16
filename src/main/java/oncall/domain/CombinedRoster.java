package oncall.domain;

public class CombinedRoster {
    private final Roster weekdayRoster;
    private final Roster holidayRoster;

    public CombinedRoster(Roster weekdayRoster, Roster holidayRoster) {
        this.weekdayRoster = weekdayRoster;
        this.holidayRoster = holidayRoster;
    }

    public Worker getWeekdayWorker(Worker previousWorker) {
        if (weekdayRoster.getNextWorker().equals(previousWorker)) {
            return weekdayRoster.getAfterNextWorker();
        }
        return weekdayRoster.getNextWorker();
    }

    public Worker getHolidayWorker(Worker previousWorker) {
        if (holidayRoster.getNextWorker().equals(previousWorker)) {
            return holidayRoster.getAfterNextWorker();
        }
        return holidayRoster.getNextWorker();
    }
}
