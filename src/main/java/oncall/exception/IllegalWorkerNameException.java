package oncall.exception;

public class IllegalWorkerNameException extends OnCallException {
    public IllegalWorkerNameException() {
        super("사원의 이름은 한 글자 이상, 5글자 이내로 작성해야 합니다.");
    }
}
