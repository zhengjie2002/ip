package tony.command;

import tony.task.TaskManager;

/**
 * Represents an abstract command to add a task.
 * The <code>AddCommand</code> class serves as a base class for specific add commands.
 */
public abstract class AddCommand extends Command {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an AddCommand with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public AddCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Executes the add command that come from a file.
     * This method must be implemented by subclasses to define how the task is added
     * when loaded from a file.
     *
     * @param taskManager The TaskManager to manage the tasks.
     */
    public abstract void executeFromFile(TaskManager taskManager);
}
