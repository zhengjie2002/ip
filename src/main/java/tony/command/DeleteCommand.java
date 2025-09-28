package tony.command;

import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class DeleteCommand extends ModificationCommand {
    public DeleteCommand(int taskIndex) {
        super(taskIndex);
    }

    /**
     * Executes the delete command.
     * Removes the specified task from the task manager, updates the data file,
     * and displays an acknowledgment message. If the task is not found,
     * an appropriate error message is displayed.
     *
     * @param taskManager The TaskManager containing the tasks.
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager for updating the task data file.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        try {
            Task removedTask = taskManager.removeTask(taskIndex);
            dataManager.updateFile(taskManager);
            ui.printDeleteAcknowledgement(removedTask, taskManager.getTaskCount());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printTaskNotFoundError();
        }
    }
}
