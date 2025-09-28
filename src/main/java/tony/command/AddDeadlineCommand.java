package tony.command;

import tony.file.DataManager;
import tony.task.Deadline;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    private final LocalDate datelineDate;

    /**
     * Constructs an AddDeadlineCommand with the specified description, deadline date, and completion status.
     *
     * @param description  The description of the Deadline task.
     * @param datelineDate The deadline date of the task.
     * @param isDone       The completion status of the Deadline task.
     */
    public AddDeadlineCommand(String description, LocalDate datelineDate, boolean isDone) {
        super(description, isDone);
        this.datelineDate = datelineDate;
    }

    /**
     * Executes the add Deadline command.
     * Creates a new Deadline task, adds it to the task manager, saves it to the data file,
     * and displays an acknowledgment message.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager for saving the task data.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        Task newDeadlineTask = new Deadline(description, datelineDate);
        taskManager.addTask(newDeadlineTask);
        dataManager.saveTask(newDeadlineTask);
        ui.printTaskAddedMessage(newDeadlineTask, taskManager.getTaskCount());
    }

    /**
     * Executes the add Event command that came from a text file.
     * Creates a new Deadline task, adds it to the task manager, and marks it as done
     * if the completion status is true.
     *
     * @param taskManager The TaskManager to manage the tasks.
     */
    @Override
    public void executeFromFile(TaskManager taskManager) {
        Task newDeadlineTask = new Deadline(description, datelineDate);
        taskManager.addTask(newDeadlineTask);

        if (isDone) {
            newDeadlineTask.markDone();
        }
    }
}