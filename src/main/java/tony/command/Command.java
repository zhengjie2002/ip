package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, DataManager dataManager);
}