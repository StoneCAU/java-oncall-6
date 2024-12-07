package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Roster {
    private WorkInfo workInfo;
    private Staffs staffs;
    private List<WorkLine> workLines;

    public Roster(WorkInfo workInfo, Staffs staffs) {
        this.workInfo = workInfo;
        this.staffs = staffs;
        this.workLines = makeWorkLines();
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

    private List<WorkLine> makeWorkLines() {
        List<DayInfo> dayInfos = getDayInfos();
        AtomicInteger weekdayIndex = new AtomicInteger(0);
        AtomicInteger weekendIndex = new AtomicInteger(0);
        return dayInfos.stream()
                .map(dayInfo -> {
                    if (dayInfo.isWeekdayAndHoliday() || !dayInfo.isWeekday()) {
                        WorkLine workLine = makeWeekendAndHolidayWorkLine(dayInfo, weekendIndex.get());
                        weekendIndex.getAndIncrement();
                        return workLine;
                    }
                    WorkLine workLine = makeWeekdayWorkLine(dayInfo, weekdayIndex.get());
                    weekdayIndex.getAndIncrement();
                    return workLine;
                }).toList();
    }

    private WorkLine makeWeekdayWorkLine(DayInfo dayInfo, int weekdayIndex) {
        List<Staff> staffList = staffs.getWeekdayStaffs();
        return new WorkLine(dayInfo, staffList.get(weekdayIndex % staffList.size()));
    }

    private WorkLine makeWeekendAndHolidayWorkLine(DayInfo dayInfo, int weekendIndex) {
        List<Staff> staffList = staffs.getWeekendStaffs();
        return new WorkLine(dayInfo, staffList.get(weekendIndex % staffList.size()));
    }

    private List<DayInfo> getDayInfos() {
        List<DayInfo> dayInfos = initDayInfo(workInfo);
        int startDay = dayInfos.get(0).getDay() + 1;
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
