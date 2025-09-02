public class Deadline extends Task {
    protected String doBy;

    public Deadline(String description, String doBy) {
        super(description);
        this.doBy = doBy;
    }

    @Override
    public String toString() {
        return ("[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + doBy + ")");
    }
}
