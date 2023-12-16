package oncall.domain;

import oncall.exception.IllegalWorkerNameException;

public class Worker {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Worker(String name) {
        validateLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateLength(String name) {
        if (name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalWorkerNameException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Worker worker = (Worker) o;

        return name.equals(worker.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
