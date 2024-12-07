package oncall.domain;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;

public enum Mouth {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(1, 28, List.of(1)),
    MARCH(3, 31, List.of()),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));

    private int monthValue;
    private int endDay;
    private List<Integer> holidays;

    Mouth(int monthValue, int endDay, List<Integer> holidays) {
        this.monthValue = monthValue;
        this.endDay = endDay;
        this.holidays = holidays;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public int getEndDay() {
        return endDay;
    }

    public List<Integer> getHolidays() {
        return holidays;
    }

    public List<Integer> getDays() {
        return IntStream.rangeClosed(1, getEndDay()).boxed().toList();
    }

    public static Mouth getMonthByMonthValue(int mouthValue) {
        return Arrays.stream(Mouth.values())
                .filter(month -> month.getMonthValue() == mouthValue)
                .findFirst()
                .orElseThrow(() -> new OncallException(ErrorMessage.INVALID_INPUT));
    }
}
