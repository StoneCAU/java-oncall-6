package oncall.validator;

import java.util.Arrays;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.domain.Staff;
import oncall.domain.Staffs;
import oncall.domain.WorkInfo;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;

public class InputValidator {
    public static WorkInfo validateWorkInfo(String input) {
        List<String> parsed = parseInfo(input);
        validateParsedInfo(parsed);
        int mountValue = parseMonthValue(parsed.get(0));
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeekByName(parsed.get(1));

        return new WorkInfo(Month.getMonthByMonthValue(mountValue), dayOfWeek);
    }

    public static List<Staff> validateStaffList(String names) {
        List<String> parsed = parseNames(names);

        if (!isValidateNames(parsed)) {
            throw new OncallException(ErrorMessage.INVALID_INPUT);
        }

        return parsed.stream()
                .map(Staff::new)
                .toList();
    }

    public static Staffs validateStaffs(List<Staff> weekdayStaffList, List<Staff> weekendStaffList) {
        if (!isValidSchedule(weekdayStaffList, weekendStaffList)) {
            throw new OncallException(ErrorMessage.INVALID_INPUT);
        }

        return new Staffs(weekdayStaffList, weekendStaffList);
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

    private static List<String> parseNames(String names) {
        List<String> parsed = Arrays.stream(names.split(",")).toList();

        if (parsed.size() < 5 || parsed.size() > 35) {
            throw new OncallException(ErrorMessage.INVALID_INPUT);
        }

        return parsed;
    }

    private static boolean isValidSchedule(List<Staff> weekdayStaffList, List<Staff> weekendStaffList) {
        return weekdayStaffList.stream()
                .allMatch(weekdayStaff -> weekendStaffList.stream()
                        .filter(weekendStaff -> weekdayStaff.getName().equals(weekendStaff.getName()))
                        .distinct()
                        .count() == 1);
    }

    private static boolean isValidateNames(List<String> names) {
        return names.stream()
                .allMatch(name -> name.length() <= 5 && !name.isEmpty());
    }

}
