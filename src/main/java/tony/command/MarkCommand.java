package tony.command;

import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class MarkCommand extends ModificationCommand {
    public MarkCommand(int taskIndex) {
        super(taskIndex);
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        try {
            int taskToMarkAsDone = taskIndex;
            Task taskMarkedAsDone = taskManager.markTaskDone(taskToMarkAsDone);
            dataManager.updateFile(taskManager);
            ui.printMarkAcknowledgement(taskMarkedAsDone);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printTaskNotFoundError();
        }
    }
}