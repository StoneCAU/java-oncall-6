package oncall.domain;

public class DayInfo {
    private Month month;
    private int day;
    private DayOfWeek dayOfWeek;

    private boolean isWeekdayAndHoliday;

    public DayInfo(Month month, int day, DayOfWeek dayOfWeek) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.isWeekdayAndHoliday = calculateWeekdayAndHoliday();
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isWeekdayAndHoliday() {
        return isWeekdayAndHoliday;
    }

    public boolean isWeekday() {
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private boolean isHoliday() {
        return month.getHolidays().contains(day);
    }

    private boolean calculateWeekdayAndHoliday() {
        return isWeekday() && isHoliday();
    }

    @Override
    public String toString() {
        return month.getMonthValue() + "월 " + day + "일 " + dayOfWeek.getName() + getWeekdayAndHolidayStr();
    }

    public String getWeekdayAndHolidayStr() {
        if (isWeekdayAndHoliday) {
            return "(휴일)";
        }
        return "";
    }
}
