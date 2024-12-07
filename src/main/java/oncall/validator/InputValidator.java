package oncall.validator;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.domain.Mouth;
import oncall.domain.WorkInfo;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;

public class InputValidator {
    public static WorkInfo validateWorkInfo(String input) {
        List<String> parsed = parseInfo(input);
        validateParsedInfo(parsed);
        int mountValue = parseMonthValue(parsed.get(0));
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeekByName(parsed.get(1));

        return new WorkInfo(Mouth.getMonthByMonthValue(mountValue), dayOfWeek);
    }

    private static List<String> parseInfo(String input) {
        return Arrays.stream(input.split(",")).toList();
    }

    private static void validateParsedInfo(List<String> parsed) {
        if (parsed.size() != 2) {
            throw new OncallException(ErrorMessage.INVALID_INPUT);
        }
    }

    private static int parseMonthValue(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new OncallException(ErrorMessage.INVALID_INPUT);
        }
    }
}
