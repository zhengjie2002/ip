package tony.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return ("[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + from + " to: " + to + ")");
    }
}
