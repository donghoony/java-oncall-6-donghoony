package oncall.domain;

public class CombinedRoster {
    private final Roster weekdayRoster;
    private final Roster holidayRoster;

    public CombinedRoster(Roster weekdayRoster, Roster holidayRoster) {
        this.weekdayRoster = weekdayRoster;
        this.holidayRoster = holidayRoster;
    }

    public Worker getWorker(RosterDay rosterDay, Worker previousWorker) {
        if (rosterDay.isHoliday()) {
            return getHolidayWorker(previousWorker);
        }
        return getWeekdayWorker(previousWorker);
    }

    private Worker getWeekdayWorker(Worker previousWorker) {
        Worker worker = weekdayRoster.checkNextWorker();
        if (worker.equals(previousWorker)) {
            return weekdayRoster.getAfterNextWorker();
        }
        return weekdayRoster.getNextWorker();
    }

    private Worker getHolidayWorker(Worker previousWorker) {
        Worker worker = holidayRoster.checkNextWorker();
        if (worker.equals(previousWorker)) {
            return holidayRoster.getAfterNextWorker();
        }
        return holidayRoster.getNextWorker();
    }
}
