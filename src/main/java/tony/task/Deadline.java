package tony.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineDate;

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
                deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
