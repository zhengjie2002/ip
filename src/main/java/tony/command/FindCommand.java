package tony.command;

import tony.file.DataManager;
import tony.task.Task;
import tony.task.TaskManager;
import tony.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * Searches for tasks containing the specified keyword, retrieves the matching tasks,
     * and displays them using the UI. If no tasks are found or an error occurs,
     * appropriate error messages are displayed.
     *
     * @param taskManager The TaskManager containing the tasks.
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager (not used in this command).
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        try {
            ArrayList<Task> matchingTasks = taskManager.findTasks(keyword);
            ui.listMatchingTasks(matchingTasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printTaskNotFoundError();
        }
    }
}