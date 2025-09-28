package tony.task;

/**
 * Represents an abstract task.
 * A <code>Task</code> object contains a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * This method must be implemented by subclasses.
     *
     * @return A string representation of the task.
     */
    @Override
    public abstract String toString();

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
}