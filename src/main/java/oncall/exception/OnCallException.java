package oncall.exception;

public class OnCallException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public OnCallException(String message) {
        super(ERROR_PREFIX + message);
    }
}
