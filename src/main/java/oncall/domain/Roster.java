package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Roster {
    private WorkInfo workInfo;
    private Staffs staffs;
    private List<Staff> weekdayStaffList;
    private List<Staff> weekendStaffList;
    private List<WorkLine> workLines = new ArrayList<>();

    public Roster(WorkInfo workInfo, Staffs staffs) {
        this.workInfo = workInfo;
        this.staffs = staffs;
        this.weekdayStaffList = staffs.getWeekdayStaffs();
        this.weekendStaffList = staffs.getWeekendStaffs();
        makeWorkLines();
    }

    public WorkInfo getWorkInfo() {
        return workInfo;
    }

    public Staffs getStaffs() {
        return staffs;
    }

    public List<WorkLine> getWorkLines() {
        return workLines;
    }

    private void makeWorkLines() {
        List<DayInfo> dayInfos = getDayInfos();
        AtomicInteger weekdayIndex = new AtomicInteger(0);
        AtomicInteger weekendIndex = new AtomicInteger(0);
        dayInfos.forEach(dayInfo -> {
                    if (dayInfo.isWeekdayAndHoliday() || !dayInfo.isWeekday()) {
                        workLines.add(makeWeekendAndHolidayWorkLine(dayInfo, weekendIndex.get()));
                        weekendIndex.getAndIncrement();
                        return;
                    }
                    workLines.add(makeWeekdayWorkLine(dayInfo, weekdayIndex.get()));
                    weekdayIndex.getAndIncrement();
                });
    }

    private WorkLine makeWeekdayWorkLine(DayInfo dayInfo, int weekdayIndex) {
        if (isInvalidSchedule(weekdayStaffList.get(weekdayIndex % weekdayStaffList.size()).getName()) && weekdayIndex != 0) {
            relocationWeekdayList(weekdayIndex);
        }
        return new WorkLine(dayInfo, weekdayStaffList.get(weekdayIndex % weekdayStaffList.size()));
    }

    private WorkLine makeWeekendAndHolidayWorkLine(DayInfo dayInfo, int weekendIndex) {
        if (isInvalidSchedule(weekendStaffList.get(weekendIndex % weekendStaffList.size()).getName()) && weekendIndex != 0) {
            relocationWeekendList(weekendIndex);
        }
        return new WorkLine(dayInfo, weekendStaffList.get(weekendIndex % weekendStaffList.size()));
    }

    private void relocationWeekdayList(int weekdayIndex) {
        Staff temp = weekdayStaffList.get(weekdayIndex % weekdayStaffList.size());
        weekdayStaffList.set(weekdayIndex % weekdayStaffList.size(), weekdayStaffList.get((weekdayIndex - 1) % weekdayStaffList.size()));
        weekdayStaffList.set((weekdayIndex - 1) % weekdayStaffList.size(), temp);
    }

    private void relocationWeekendList(int weekendIndex) {
        Staff temp = weekendStaffList.get(weekendIndex % weekendStaffList.size());
        weekendStaffList.set(weekendIndex % weekendStaffList.size(), weekendStaffList.get((weekendIndex - 1) % weekendStaffList.size()));
        weekendStaffList.set((weekendIndex - 1) % weekendStaffList.size(), temp);
    }

    private boolean isInvalidSchedule(String name) {
        if (workLines.isEmpty()) return false;
        return workLines.get(workLines.size() - 1).getStaff().getName().equals(name);
    }

    private List<DayInfo> getDayInfos() {
        List<DayInfo> dayInfos = initDayInfo(workInfo);
        int startDay = dayInfos.get(dayInfos.size() - 1).getDay() + 1;
        int endDay = workInfo.getMonth().getEndDay();
        int index = 0;

        for (int day = startDay; day <= endDay; day++) {
            dayInfos.add(new DayInfo(workInfo.getMonth(), day, DayOfWeek.values()[index % 7]));
            index++;
        }

        return dayInfos;
    }

    private List<DayInfo> initDayInfo(WorkInfo workInfo) {
        List<DayInfo> dayInfos = new ArrayList<>();
        int day = 1;

        for (int i = workInfo.getDayOfWeek().ordinal(); i <= 6; i++) {
            dayInfos.add(new DayInfo(workInfo.getMonth(), day, DayOfWeek.values()[i]));
            day++;
        }

        return dayInfos;
    }
}
