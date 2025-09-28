package tony.command;

import tony.exceptions.TaskAlreadyMarkedException;
import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class MarkCommand extends ModificationCommand {
    public MarkCommand(int taskIndex) {
        super(taskIndex);
    }


    /**
     * Executes the mark command.
     * Marks the specified task as done, updates the data file, and displays
     * an acknowledgment message. If the task is not found or already marked,
     * appropriate error messages are displayed.
     *
     * @param taskManager The TaskManager containing the tasks.
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager for updating the task data file.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        try {
            int taskToMarkAsDone = taskIndex;
            Task taskMarkedAsDone = taskManager.markTaskDone(taskToMarkAsDone);
            dataManager.updateFile(taskManager);
            ui.printMarkAcknowledgement(taskMarkedAsDone);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printTaskNotFoundError();
        } catch (TaskAlreadyMarkedException e) {
            ui.printTaskAlreadyMarkedError();
        }
    }
}