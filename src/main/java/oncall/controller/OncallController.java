package oncall.controller;

import java.util.List;
import oncall.domain.Roster;
import oncall.domain.Staff;
import oncall.domain.Staffs;
import oncall.domain.WorkInfo;
import oncall.exception.OncallException;
import oncall.validator.InputValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    public void run() {
        WorkInfo workInfo = getWorkInfo();
        Staffs staffs = getStaffs();
        getRoster(workInfo, staffs);
    }

    private WorkInfo getWorkInfo() {
        while (true) {
            try {
                String input = InputView.inputMouthAndDay();
                return InputValidator.validateWorkInfo(input);
            } catch (OncallException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Staffs getStaffs() {
        while (true) {
            try {
                List<Staff> weekdayStaffList = InputValidator.validateStaffList(InputView.inputWeekdayNames());
                List<Staff> weekendStaffList = InputValidator.validateStaffList(InputView.inputWeekendNames());

                return InputValidator.validateStaffs(weekdayStaffList,weekendStaffList);
            } catch (OncallException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void getRoster(WorkInfo workInfo, Staffs staffs) {
        Roster roster = new Roster(workInfo, staffs);
        OutputView.printRoster(roster);
    }
}
