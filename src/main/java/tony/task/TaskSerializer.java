package tony.task;

/**
 * The `TaskSerializer` class provides functionality to serialize tasks into
 * string representations for storage. It supports different task types such as
 * `Todo`, `Deadline`, and `Event`.
 */
public class TaskSerializer {
    /**
     * Serializes a given task into a string representation for storing to file.
     * The string format includes the task type, completion status, description, and additional
     * details specific to the task type (e.g., deadline date for Deadline tasks,
     * start and end dates for Event tasks).
     *
     * @param task The task to be serialized.
     * @return A string representation of the task.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public String serializeTask(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("todo|");
        } else if (task instanceof Deadline) {
            sb.append("deadline|");
        } else if (task instanceof Event) {
            sb.append("event|");
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }
        sb.append(task.isDone() ? "1|" : "0|");
        sb.append(task.getDescription()).append("|");
        if (task instanceof Deadline) {
            sb.append(((Deadline) task).getDeadlineDate()).append("|");
        } else if (task instanceof Event) {
            sb.append(((Event) task).getFrom()).append("|");
            sb.append(((Event) task).getTo()).append("|");
        }
        return sb.toString();
    }
}
