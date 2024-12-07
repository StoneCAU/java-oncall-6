package oncall.domain;

public class WorkLine {
    private DayInfo dayInfo;
    private Staff staff;

    public WorkLine(DayInfo dayInfo, Staff staff) {
        this.dayInfo = dayInfo;
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    @Override
    public String toString() {
        return dayInfo.toString() + " " + staff.getName();
    }
}
