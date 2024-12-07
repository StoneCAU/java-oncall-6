package oncall.domain;

import java.util.Arrays;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private String name;

    DayOfWeek(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DayOfWeek getDayOfWeekByName(String name) {
        return Arrays.stream(DayOfWeek.values()).filter(dw -> dw.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new OncallException(ErrorMessage.INVALID_INPUT));
    }
}
