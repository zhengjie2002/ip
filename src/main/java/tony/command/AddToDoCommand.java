package tony.command;

import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.task.Todo;
import tony.ui.Ui;

public class AddToDoCommand extends AddCommand {
    public AddToDoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newTask = new Todo(description);
        taskManager.addTask(newTask);
        dataManager.saveTask(newTask);
        ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
    }

    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newTask = new Todo(description);
        taskManager.addTask(newTask);
        if (isDone) {
            newTask.markDone();
        }
    }
}