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

    /**
     * Executes the add ToDo command.
     * Creates a new ToDo task, adds it to the task manager, saves it to the data file,
     * and displays an acknowledgment message.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager for saving the task data.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newTask = new Todo(description);
        taskManager.addTask(newTask);
        dataManager.saveTask(newTask);
        ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
    }

    /**
     * Executes the add ToDo command that came from a text file.
     * Creates a new ToDo task, adds it to the task manager, and marks it as done
     * if the completion status is true.
     *
     * @param taskManager The TaskManager to manage the tasks.
     */
    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newTask = new Todo(description);
        taskManager.addTask(newTask);
        if (isDone) {
            newTask.markDone();
        }
    }
}