package oncall.domain;

public class WorkInfo {
    private Mouth month;
    private DayOfWeek dayOfWeek;

    public WorkInfo(Mouth month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public Mouth getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
