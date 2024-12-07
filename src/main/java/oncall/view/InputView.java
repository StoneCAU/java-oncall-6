package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String NEW_LINE = System.lineSeparator();

    public static String inputMouthAndDay() {
        System.out.println("비상 근무를 배정할 월과 시작 요일을 입력하세요>");
        return input();
    }

    private static String input() {
        return Console.readLine();
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
