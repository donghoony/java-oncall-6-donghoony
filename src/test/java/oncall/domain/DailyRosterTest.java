package oncall.domain;

import java.time.DayOfWeek;
import java.time.MonthDay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DailyRosterTest {
    @Test
    @DisplayName("포맷 형식이 정확하게 반영된다")
    void formatTest() {
        // given
        DailyRoster dailyRoster = new DailyRoster(
                new RosterDay(MonthDay.of(12, 25), DayOfWeek.FRIDAY),
                new Worker("pobi"));

        // when, then
        Assertions.assertThat(dailyRoster).hasToString("12월 25일 금(휴일) pobi");
    }
}
