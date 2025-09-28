package tony.command;

import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class DeleteCommand extends ModificationCommand {
    public DeleteCommand(int taskIndex) {
        super(taskIndex);
    }

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
