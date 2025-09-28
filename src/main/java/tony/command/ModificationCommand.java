package tony.command;

public abstract class ModificationCommand extends Command {
    protected int taskIndex;

    public ModificationCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

}
