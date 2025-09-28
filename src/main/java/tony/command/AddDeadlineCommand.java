package tony.command;

import tony.file.DataManager;
import tony.task.Deadline;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

public class AddDeadlineCommand extends AddCommand {
    private String by;

    public AddDeadlineCommand(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newDeadlineTask = new Deadline(description, by);
        taskManager.addTask(newDeadlineTask);
        dataManager.saveTask(newDeadlineTask);
        ui.printTaskAddedMessage(newDeadlineTask, taskManager.getTaskCount());
    }

    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newDeadlineTask = new Deadline(description, by);
        taskManager.addTask(newDeadlineTask);

        if (isDone) {
            newDeadlineTask.markDone();
        }
    }
}