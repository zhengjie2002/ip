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