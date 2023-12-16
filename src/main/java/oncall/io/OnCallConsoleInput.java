package oncall.io;

import camp.nextstep.edu.missionutils.Console;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import oncall.domain.MonthlyCalendar;
import oncall.domain.Worker;
import oncall.exception.IllegalInputException;

public class OnCallConsoleInput implements OnCallInput {
    private static final String DELIMITER = ",";

    @Override
    public MonthlyCalendar getMonthAndDayOfWeek() {

        String input = Console.readLine();
        String[] splitInput = input.split(DELIMITER);

        validateMonthDayInput(splitInput);

        Month month = parseMonth(splitInput[0]);
        DayOfWeek dayOfWeek = parseDayOfWeek(splitInput[1]);
        return new MonthlyCalendar(month, dayOfWeek);
    }

    @Override
    public List<Worker> getWorkers() {
        String input = Console.readLine();
        String[] split = input.split(DELIMITER);
        return Arrays.stream(split)
                .map(Worker::new)
                .toList();
    }

    private DayOfWeek parseDayOfWeek(String s) {
        final Map<String, DayOfWeek> dayOfWeekMap = Map.of(
                "월", DayOfWeek.MONDAY,
                "화", DayOfWeek.TUESDAY,
                "수", DayOfWeek.WEDNESDAY,
                "목", DayOfWeek.THURSDAY,
                "금", DayOfWeek.FRIDAY,
                "토", DayOfWeek.SATURDAY,
                "일", DayOfWeek.SUNDAY
        );

        if (!dayOfWeekMap.containsKey(s)) {
            throw new IllegalInputException();
        }
        return dayOfWeekMap.get(s);
    }

    private Month parseMonth(String s) {
        try {
            int month = Integer.parseInt(s);
            return Month.of(month);
        } catch (NumberFormatException | DateTimeException e) {
            throw new IllegalInputException();
        }
    }

    private void validateMonthDayInput(String[] input) {
        if (input.length != 2) {
            throw new IllegalInputException();
        }
    }
}
