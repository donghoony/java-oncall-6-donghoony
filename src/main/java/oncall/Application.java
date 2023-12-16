package oncall;

import oncall.controller.OnCallController;
import oncall.io.OnCallConsoleInput;
import oncall.io.OnCallConsoleOutput;
import oncall.io.OnCallInput;
import oncall.io.OnCallOutput;
import oncall.service.RosterService;

public class Application {
    public static void main(String[] args) {
        OnCallInput input = new OnCallConsoleInput();
        OnCallOutput output = new OnCallConsoleOutput();

        RosterService rosterService = new RosterService();
        OnCallController controller = new OnCallController(rosterService, input, output);

        controller.makeRoster();
    }
}
