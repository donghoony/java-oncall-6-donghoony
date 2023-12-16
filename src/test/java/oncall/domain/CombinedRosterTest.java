package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import oncall.exception.IllegalCombinedRosterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CombinedRosterTest {
    @Test
    @DisplayName("한 쪽에만 존재하는 근무자가 있다면 예외를 발생한다.")
    void invalidCombination() {
        // given
        List<Worker> workers1 = makeWorkers("pobi", "hoony", "crong", "woowa", "dong");
        List<Worker> workers2 = makeWorkers("pobi", "hoony", "crong", "woowa", "gyuri");

        // when
        Roster roster1 = new Roster(workers1);
        Roster roster2 = new Roster(workers2);

        // then
        assertThatThrownBy(() -> new CombinedRoster(roster1, roster2))
                .isInstanceOf(IllegalCombinedRosterException.class);

    }

    @Test
    @DisplayName("두 명단이 동일한 근무자인 경우, 올바르게 생성한다")
    void validCombination() {
        // given
        List<Worker> workers1 = makeWorkers("pobi", "hoony", "crong", "woowa", "dong");
        List<Worker> workers2 = makeWorkers("pobi", "hoony", "crong", "woowa", "dong");
        // when

        Roster roster1 = new Roster(workers1);
        Roster roster2 = new Roster(workers2);
        // then
        assertDoesNotThrow(() -> new CombinedRoster(roster1, roster2));

    }


    List<Worker> makeWorkers(String... names) {
        List<Worker> workers = new ArrayList<>();
        for (String name : names) {
            workers.add(new Worker(name));
        }
        return workers;
    }
}
