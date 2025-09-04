public class Command {
    private CommandType type;
    private String description;
    private String from;
    private String to;
    private String by;
    private int taskIndex;

    public Command(CommandType type) {
        this.type = type;
    }

    // Constructor for mark and unmark commands
    public Command(CommandType type, int taskIndex) {
        this.type = type;
        this.taskIndex = taskIndex;
    }

    // Constructor for todo commands
    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    // Constructor for event commands
    public Command(CommandType type, String description, String from, String to) {
        this.type = type;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    // Constructor for deadline commands
    public Command(CommandType type, String description, String by) {
        this.type = type;
        this.description = description;
        this.by = by;
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