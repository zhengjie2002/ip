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
