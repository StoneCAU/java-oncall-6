package oncall.controller;

import oncall.domain.WorkInfo;
import oncall.exception.OncallException;
import oncall.validator.InputValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    public void run() {
        WorkInfo workInfo = getWorkInfo();
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
}
