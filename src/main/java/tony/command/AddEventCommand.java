package tony.command;

import tony.file.DataManager;
import tony.task.Event;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

import java.time.LocalDate;

public class AddEventCommand extends AddCommand {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an AddEventCommand with the specified description, start date, end date, and completion status.
     *
     * @param description The description of the Event task.
     * @param from        The start date of the Event task.
     * @param to          The end date of the Event task.
     * @param isDone      The completion status of the Event task.
     */
    public AddEventCommand(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    /**
     * Executes the add Event command.
     * Creates a new Event task, adds it to the task manager, saves it to the data file,
     * and displays an acknowledgment message.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager for saving the task data.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newEventTask = new Event(description, from, to);
        taskManager.addTask(newEventTask);
        dataManager.saveTask(newEventTask);
        ui.printTaskAddedMessage(newEventTask, taskManager.getTaskCount());

    }

    /**
     * Executes the add Event command that came from a text file.
     * Creates a new Event task, adds it to the task manager, and marks it as done
     * if the completion status is true.
     *
     * @param taskManager The TaskManager to manage the tasks.
     */
    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newEventTask = new Event(description, from, to);
        taskManager.addTask(newEventTask);
        if (isDone) {
            newEventTask.markDone();
        }
    }
}
