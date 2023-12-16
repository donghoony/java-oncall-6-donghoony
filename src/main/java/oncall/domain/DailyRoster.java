package oncall.domain;

public class DailyRoster {
    private final RosterDay rosterDay;
    private final Worker worker;

    public DailyRoster(RosterDay rosterDay, Worker worker) {
        this.rosterDay = rosterDay;
        this.worker = worker;
    }

    @Override
    public String toString() {
        return rosterDay.toString() + " " + worker.name();
    }
}
