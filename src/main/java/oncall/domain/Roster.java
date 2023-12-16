package oncall.domain;

import java.util.List;
import java.util.ListIterator;
import oncall.exception.DuplicateWorkerException;
import oncall.exception.IllegalRoasterSizeException;

public class Roster {
    private static final int MIN_WORKERS = 5;
    private static final int MAX_WORKERS = 35;

    private final List<Worker> workers;
    private ListIterator<Worker> iterator;
    private boolean skipped = false;

    public Roster(List<Worker> workers) {
        validateRoster(workers);
        this.workers = workers;
        this.iterator = workers.listIterator();
    }

    public boolean isSameAs(Roster roster) {
        int count = (int) workers.stream()
                .filter(roster::contains)
                .count();

        return count == workers.size();
    }

    public Worker getNextWorker() {
        initIterator();
        if (skipped) {
            return getSkippedWorker();
        }
        return iterator.next();
    }

    private Worker getSkippedWorker() {
        initIterator();
        Worker worker = iterator.next();
        skipped = false;
        initIterator();
        iterator.next();

        return worker;
    }

    public Worker getAfterNextWorker() {
        getNextWorker();
        Worker nextAfterWorker = getNextWorker();
        skipped = true;

        moveIteratorPrevious();
        moveIteratorPrevious();
        return nextAfterWorker;
    }

    public Worker checkNextWorker() {
        initIterator();
        Worker expectedWorker = iterator.next();
        moveIteratorPrevious();
        return expectedWorker;
    }

    private void initIterator() {
        if (!iterator.hasNext()) {
            iterator = workers.listIterator();
        }
    }

    private void moveIteratorPrevious() {
        if (!iterator.hasPrevious()) {
            iterator = workers.listIterator(workers.size() - 1);
        }
        iterator.previous();
    }


    private boolean contains(Worker worker) {
        return workers.contains(worker);
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
