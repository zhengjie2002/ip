package tony.command;

import tony.exceptions.TaskAlreadyMarkedException;
import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class UnmarkCommand extends ModificationCommand {
    public UnmarkCommand(int taskIndex) {
        super(taskIndex);
    }

    /**
     * Executes the unmark command.
     * Marks the specified task as not done, updates the data file, and displays
     * an acknowledgment message. If the task is not found or already unmarked,
     * appropriate error messages are displayed.
     *
     * @param taskManager The TaskManager containing the tasks.
     * @param ui The user interface for displaying messages.
     * @param dataManager The DataManager for updating the task data file.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        try {
            int taskToMarkUndone = taskIndex;
            Task taskUnmarked = taskManager.markTaskUndone(taskToMarkUndone);
            dataManager.updateFile(taskManager);
            ui.printUnmarkAcknowledgement(taskUnmarked);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printTaskNotFoundError();
        } catch (TaskAlreadyMarkedException e) {
            ui.printTaskAlreadyMarkedError();
        }
    }
}
