package oncall.exception;

public class DuplicateWorkerException extends OnCallException {
    public DuplicateWorkerException() {
        super("중복된 근무자가 존재합니다.");
    }
}
