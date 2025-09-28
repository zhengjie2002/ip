package tony.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineDate;
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy";

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    @Override
    public String toString() {
        return ("[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " +
                deadlineDate.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")");
    }
}
