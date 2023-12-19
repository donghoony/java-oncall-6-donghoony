package oncall.exception;

public class IllegalCombinedRosterException extends OnCallException {
    public IllegalCombinedRosterException() {
        super("평일 근무자와 휴일 근무자는 서로 같아야 합니다.");
    }
}
