package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class Staffs {
    List<Staff> weekdayStaffs;
    List<Staff> weekendStaffs;

    public Staffs(List<Staff> weekdayStaffs, List<Staff> weekendStaffs) {
        this.weekdayStaffs = weekdayStaffs;
        this.weekendStaffs = weekendStaffs;
    }

    public List<Staff> getWeekdayStaffs() {
        return new ArrayList<>(weekdayStaffs);
    }

    public List<Staff> getWeekendStaffs() {
        return new ArrayList<>(weekendStaffs);
    }
}
