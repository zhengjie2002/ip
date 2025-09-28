package tony.command;

import tony.task.TaskManager;

public abstract class AddCommand extends Command {
    protected String description;
    protected boolean isDone;

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

    public abstract void executeFromFile(TaskManager taskManager);
}
