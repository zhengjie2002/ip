package tony.command;

/**
 * Represents an abstract command that modifies a task.
 * This class extends the `Command` class and provides a base implementation
 * for commands that require a task index to perform modifications.
 */
public abstract class ModificationCommand extends Command {
    protected int taskIndex;

    public ModificationCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

}
