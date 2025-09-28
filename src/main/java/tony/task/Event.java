package tony.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /** The format used for displaying the event date. */
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    /**
     * Constructs a new Event object with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string formatted for printing an event.
     * The string includes the completion status, description,
     * and the formatted start and end dates.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return ("[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " +
                from.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + " to: " +
                to.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")");
    }
}
