package oncall.domain;

import java.util.List;
import oncall.exception.DuplicateWorkerException;
import oncall.exception.IllegalRoasterSizeException;

public class OnCallRoster {
    private static final int MIN_WORKERS = 5;
    private static final int MAX_WORKERS = 35;

    private final List<Worker> workers;

    public OnCallRoster(List<Worker> workers) {
        validateRoster(workers);
        this.workers = workers;
    }

    private void validateRoster(List<Worker> workers) {
        validateSize(workers);
        validateUniqueNames(workers);
    }

    private void validateSize(List<Worker> workers) {
        int size = workers.size();
        if (size < MIN_WORKERS || size > MAX_WORKERS) {
            throw new IllegalRoasterSizeException();
        }
    }

    private void validateUniqueNames(List<Worker> workers) {
        int count = (int) workers.stream()
                .distinct().count();

        if (count != workers.size()) {
            throw new DuplicateWorkerException();
        }
    }
}
