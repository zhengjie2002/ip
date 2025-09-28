package tony.command;

import tony.file.DataManager;
import tony.task.Deadline;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    private final LocalDate datelineDate;

    public AddDeadlineCommand(String description, LocalDate datelineDate, boolean isDone) {
        super(description, isDone);
        this.datelineDate = datelineDate;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newDeadlineTask = new Deadline(description, datelineDate);
        taskManager.addTask(newDeadlineTask);
        dataManager.saveTask(newDeadlineTask);
        ui.printTaskAddedMessage(newDeadlineTask, taskManager.getTaskCount());
    }

    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newDeadlineTask = new Deadline(description, datelineDate);
        taskManager.addTask(newDeadlineTask);

        if (isDone) {
            newDeadlineTask.markDone();
        }
    }
}