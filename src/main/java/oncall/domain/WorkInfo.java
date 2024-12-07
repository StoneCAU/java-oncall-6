package oncall.domain;

public class WorkInfo {
    private Month month;
    private DayOfWeek dayOfWeek;

    public WorkInfo(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
