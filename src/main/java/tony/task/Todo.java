package tony.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo object.
     * The string includes the completion status, and description.
     *
     * @return A string representation of the Todo object for printing to console.
     */
    @Override
    public String toString() {
        return ("[T]" + (isDone ? "[X] " : "[ ] ") + description);
    }
}
