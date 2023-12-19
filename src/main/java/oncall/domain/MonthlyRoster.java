package oncall.domain;

import java.util.List;
import java.util.StringJoiner;

public class MonthlyRoster {
    private final List<DailyRoster> dailyRosters;

    public MonthlyRoster(List<DailyRoster> dailyRosters) {
        this.dailyRosters = dailyRosters;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        dailyRosters.stream()
                .map(DailyRoster::toString)
                .forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
