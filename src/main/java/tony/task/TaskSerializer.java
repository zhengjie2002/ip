package tony.task;

public class TaskSerializer {
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
