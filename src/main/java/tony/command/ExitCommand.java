package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        ui.printGoodbye();
    }
}
