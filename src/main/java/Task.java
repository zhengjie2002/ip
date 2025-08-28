public class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + description);
        System.out.println("____________________________________________________________");
    }

    public String getDescription() {
        return this.description;
    }
}