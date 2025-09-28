package tony.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return ("[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " +
                from.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + " to: " +
                to.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")");
    }
}
