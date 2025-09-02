public class Todo extends Task {
    public Todo(String description) {
        super(description.substring(4).trim());
    }

    @Override
    public String toString() {
        return ("[T]" + (isDone ? "[X] " : "[ ] ") + description);
    }
}
