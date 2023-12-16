package oncall.domain;

import oncall.exception.IllegalCombinedRosterException;

public class CombinedRoster {
    private final Roster weekdayRoster;
    private final Roster holidayRoster;

    public CombinedRoster(Roster weekdayRoster, Roster holidayRoster) {
        validateIdenticalWorkers(weekdayRoster, holidayRoster);
        this.weekdayRoster = weekdayRoster;
        this.holidayRoster = holidayRoster;
    }


    public Worker getWorker(RosterDay rosterDay, Worker previousWorker) {
        if (rosterDay.isHoliday()) {
            return getHolidayWorker(previousWorker);
        }
        return getWeekdayWorker(previousWorker);
    }

    private void validateIdenticalWorkers(Roster weekdayRoster, Roster holidayRoster) {
        if (!weekdayRoster.isSameAs(holidayRoster)) {
            throw new IllegalCombinedRosterException();
        }
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
