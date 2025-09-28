package tony.command;

import tony.file.DataManager;
import tony.task.TaskManager;
import tony.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     * Displays a goodbye message to the user using the UI.
     *
     * @param taskManager The TaskManager (not used in this command).
     * @param ui          The user interface for displaying messages.
     * @param dataManager The DataManager (not used in this command).
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        ui.printGoodbye();
    }
}
