package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * Subclasses of `Command` must implement the `execute` method to define specific command behavior.
 */
public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, DataManager dataManager);
}