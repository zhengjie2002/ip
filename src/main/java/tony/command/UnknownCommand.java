package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        ui.printUnknownCommandMessage();
    }
}
