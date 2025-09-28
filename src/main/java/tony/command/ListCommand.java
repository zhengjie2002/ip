package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;


public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Retrieves all tasks from the task manager and displays them using the UI.
     *
     * @param taskManager The TaskManager containing the tasks.
     * @param ui          The user interface for displaying the tasks.
     * @param dataManager The DataManager (not used in this command).
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        ui.listAllTasks(taskManager.getTasks());
    }
}
