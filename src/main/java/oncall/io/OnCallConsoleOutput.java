package oncall.io;

import oncall.domain.MonthlyRoster;

public class OnCallConsoleOutput implements OnCallOutput {

    @Override
    public void printAskingMonthAndWeekDay() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }

    @Override
    public void printAskingWeekDayWorkers() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    @Override
    public void printAskingHolidayWorkers() {
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    @Override
    public void printRoster(MonthlyRoster monthlyRoster) {
        System.out.println(monthlyRoster);
    }

}
