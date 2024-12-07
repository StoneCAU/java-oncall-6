package oncall.view;

import oncall.domain.Roster;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void printRoster(Roster roster) {
        printNewLine();
        roster.getWorkLines()
                .forEach(System.out::println);
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    public static void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }
}
