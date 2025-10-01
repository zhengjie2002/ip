package tony.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. This class extends the `Task` class
 * and includes additional functionality for handling date of deadline.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;

    /** The format used for displaying the deadline date. */
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";

    /**
     * Constructs a new Deadline object representing a task with a deadline.
     * Object has the specified task description and deadline date.
     *
     * @param description  The description of the task.
     * @param deadlineDate The date the task is due.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Returns the string formatted for printing a task with deadline.
     * The string includes the task type, completion status, description, and formatted deadline date.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return ("[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " +
                deadlineDate.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")");
    }
}
