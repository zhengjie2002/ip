package tony.command;

import tony.file.DataManager;
import tony.task.Event;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class AddEventCommand extends AddCommand {
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newEventTask = new Event(description, from, to);
        taskManager.addTask(newEventTask);
        dataManager.saveTask(newEventTask);
        ui.printTaskAddedMessage(newEventTask, taskManager.getTaskCount());

    }

    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newEventTask = new Event(description, from, to);
        taskManager.addTask(newEventTask);
        if (isDone) {
            newEventTask.markDone();
        }
    }
}
