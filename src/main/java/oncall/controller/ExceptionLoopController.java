package oncall.controller;

import java.util.function.Supplier;
import oncall.exception.OnCallException;

public abstract class ExceptionLoopController {
    protected <T> T repeatUntilValid(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
