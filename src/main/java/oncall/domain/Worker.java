package oncall.domain;

import oncall.exception.IllegalWorkerNameException;

public record Worker(String name) {
    private static final int MAX_NAME_LENGTH = 5;

    public Worker {
        validateLength(name);
    }

    private void validateLength(String name) {
        if (name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalWorkerNameException();
        }
    }
}
