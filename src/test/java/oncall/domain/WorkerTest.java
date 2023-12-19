package oncall.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import oncall.exception.IllegalWorkerNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WorkerTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "donghoony"})
    @DisplayName("근무자의 이름이 다섯 글자보다 길거나, 비어있는 경우 예외를 발생한다")
    void invalidWorkerName(String name) {
        Assertions.assertThatThrownBy(() -> new Worker(name))
                .isInstanceOf(IllegalWorkerNameException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "aru", "hoony", "a", "po"})
    @DisplayName("근무자의 이름이 조건에 부합한다면 정상적으로 생성한다")
    void validWorkerName(String name) {
        assertDoesNotThrow(() -> new Worker(name));
    }

}
