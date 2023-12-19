package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import oncall.exception.DuplicateWorkerException;
import oncall.exception.IllegalRoasterSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RosterTest {
    @Test
    @DisplayName("명단 길이가 작은 경우, 예외를 발생한다")
    void shortRosterSize() {
        // given
        List<Worker> workers = List.of(
                new Worker("pobi"),
                new Worker("crong"),
                new Worker("woowa")
        );

        // when, then
        assertThatThrownBy(() -> new Roster(workers))
                .isInstanceOf(IllegalRoasterSizeException.class);
    }

    @Test
    @DisplayName("명단 길이가 너무 긴 경우, 예외를 발생한다")
    void tooLongRosterSize() {
        // given
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            workers.add(new Worker("w" + i));
        }
        // when, then
        assertThatThrownBy(() -> new Roster(workers))
                .isInstanceOf(IllegalRoasterSizeException.class);
    }

    @Test
    @DisplayName("사원 이름이 중복되는 경우, 예외를 발생한다")
    void duplicateWorkerName() {
        // given
        List<Worker> workers = List.of(
                new Worker("pobi"),
                new Worker("crong"),
                new Worker("hoony"),
                new Worker("pobi"),
                new Worker("woowa")
        );
        // when, then
        assertThatThrownBy(() -> new Roster(workers))
                .isInstanceOf(DuplicateWorkerException.class);
    }

    @Test
    @DisplayName("조건에 부합하는 경우 올바르게 생성한다")
    void validRoster() {
        // given
        List<Worker> workers = List.of(
                new Worker("pobi"),
                new Worker("crong"),
                new Worker("dong"),
                new Worker("hoony"),
                new Worker("woowa")
        );
        // when, then
        assertDoesNotThrow(() -> new Roster(workers));
    }

    @Test
    @DisplayName("다음 순번이 누구인지 정확하게 확인한다.")
    void checkNextWorker() {
        // given
        Roster roster = makeDummyRoster();
        roster.getNextWorker();
        roster.getNextWorker();
        roster.getNextWorker();

        // when
        Worker worker = roster.checkNextWorker();
        // then
        assertThat(worker.name()).isEqualTo("hoony");
    }

    @Test
    @DisplayName("다음 순번을 정확하게 가져온다")
    void getNextWorker() {
        // given
        Roster roster = makeDummyRoster();

        // when
        Worker firstWorker = roster.getNextWorker();
        roster.getNextWorker();
        roster.getNextWorker();
        roster.getNextWorker();
        roster.getNextWorker();
        Worker cycledWorker = roster.getNextWorker();

        // then
        assertThat(firstWorker.name()).isEqualTo("pobi");
        assertThat(cycledWorker.name()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("두 명단이 같은지 확인한다")
    void checkSameRoster() {
        // given
        Roster roster1 = makeDummyRoster();

        List<Worker> workers2 = List.of(
                new Worker("hoony"),
                new Worker("crong"),
                new Worker("pobi"),
                new Worker("woowa"),
                new Worker("dong")
        );
        Roster roster2 = new Roster(workers2);

        // when, then
        assertThat(roster1.isSameAs(roster2)).isTrue();
    }


    @Test
    @DisplayName("다다음 순번을 정확하게 가져온 뒤, 이터레이터를 올바르게 조정한다")
    void getNextAfterWorker() {
        // given
        Roster roster = makeDummyRoster();

        // when
        Worker firstWorker = roster.getAfterNextWorker();
        Worker secondWorker = roster.getNextWorker();
        Worker thirdWorker = roster.getNextWorker();

        // then
        assertThat(firstWorker.name()).isEqualTo("crong");
        assertThat(secondWorker.name()).isEqualTo("pobi");
        assertThat(thirdWorker.name()).isEqualTo("dong");
    }

    @Test
    @DisplayName("다다음 순번이 이터레이터의 끝에 걸리더라도 올바르게 조정한다")
    void previousCheck() {
        // given
        Roster roster = makeDummyRoster();

        // when
        for(int i = 0; i < 4; i++) {
            roster.getNextWorker();
        }
        Worker firstWorker = roster.getAfterNextWorker();
        Worker secondWorker = roster.getNextWorker();
        Worker thirdWorker = roster.getNextWorker();
        // then
        assertThat(firstWorker.name()).isEqualTo("pobi");
        assertThat(secondWorker.name()).isEqualTo("woowa");
        assertThat(thirdWorker.name()).isEqualTo("crong");
    }

    @Test
    @DisplayName("건너뛰었을 때, 다음 순번을 정확하게 확인한다")
    void checkAfterNext() {
        // given
        Roster roster = makeDummyRoster();
        for(int i = 0; i < 4; i++) {
            roster.getNextWorker();
        }
        // when
        Worker firstWorker = roster.getAfterNextWorker();
        Worker checkedNextWorker1 = roster.checkNextWorker();
        Worker checkedNextWorker2 = roster.checkNextWorker();

        // then
        assertThat(firstWorker.name()).isEqualTo("pobi");
        assertThat(checkedNextWorker1.name()).isEqualTo("woowa");
        assertThat(checkedNextWorker2.name()).isEqualTo("woowa");

    }

    Roster makeDummyRoster() {
        List<Worker> workers = List.of(
                new Worker("pobi"),
                new Worker("crong"),
                new Worker("dong"),
                new Worker("hoony"),
                new Worker("woowa")
        );
        return new Roster(workers);
    }
}
