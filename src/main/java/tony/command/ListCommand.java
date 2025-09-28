package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;


public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        ui.listAllTasks(taskManager.getTasks());
    }
}
