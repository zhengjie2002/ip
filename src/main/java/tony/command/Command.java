package tony.command;

public class Command {
    private CommandType type;
    private String description;
    private String from;
    private String to;
    private String by;
    private boolean isDone;
    private int taskIndex;

    public Command(CommandType type) {
        this.type = type;
    }

    // Constructor for mark and unmark commands and delete commands
    public Command(CommandType type, int taskIndex) {
        this.type = type;
        this.taskIndex = taskIndex;
    }

    // Constructor for todo commands
    public Command(CommandType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    // Constructor for event commands
    public Command(CommandType type, String description, String from, String to, boolean isDone) {
        this.type = type;
        this.description = description;
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    // Constructor for deadline commands
    public Command(CommandType type, String description, String by, boolean isDone) {
        this.type = type;
        this.description = description;
        this.by = by;
        this.isDone = isDone;
    }

    public Command(CommandType type, String keyword) {
        this.type = type;
        this.description = keyword;
    }

    public boolean isDone() {
        return isDone;
    }

    public CommandType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBy() {
        return by;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

}