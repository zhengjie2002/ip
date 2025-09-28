package tony.file;

import tony.command.AddCommand;
import tony.command.AddDeadlineCommand;
import tony.command.AddEventCommand;
import tony.command.AddToDoCommand;

import tony.exceptions.FileDataCorruptedException;

import tony.task.Task;
import tony.task.TaskManager;
import tony.task.TaskSerializer;
import tony.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DataManager {

    private static final String DELIMITER = "\\|";
    private final String filePath;
    private FileUtils fileUtils;
    private final TaskSerializer taskSerializer = new TaskSerializer();
    private final Ui ui = new Ui();

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    public void loadData(TaskManager taskManager) {
        fileUtils = new FileUtils(filePath);
        fileUtils.loadFile();
        ArrayList<String> dataStrings = fileUtils.readFile();
        try {
            parseFile(dataStrings, taskManager);
        } catch (Exception e) {
            ui.printFileCorruptedError();
            taskManager.clearTasks();
        }
    }

    private void parseFile(ArrayList<String> dataStrings, TaskManager taskManager) {

        for (String line : dataStrings) {
            if (line.trim().isEmpty()) continue;

            String[] words = line.split(DELIMITER);
            String commandWord = words[0];
            boolean isDone = words[1].equals("1");
            AddCommand addFromFileCommand;

            switch (commandWord) {
            case "todo":
                addFromFileCommand = new AddToDoCommand(words[2], isDone);
                break;
            case "deadline":
                addFromFileCommand = new AddDeadlineCommand(words[2], words[3], isDone);
                break;
            case "event":
                addFromFileCommand = new AddEventCommand(words[2], words[3], words[4], isDone);
                break;
            default:
                throw new FileDataCorruptedException();
            }
            addFromFileCommand.executeFromFile(taskManager);
        }
    }

    public void saveTask(Task newTask) {
        try {
            String taskText = taskSerializer.serializeTask(newTask);
            fileUtils.appendToFile(taskText);
        } catch (IOException e) {
            ui.printWritingToFileError(e.getMessage());
        }
    }

    public void saveAllTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            saveTask(task);
        }
    }

    public void updateFile(TaskManager taskManager) {
        try {
            fileUtils.clearFile();
            saveAllTasks(taskManager.getTasks());
        } catch (IOException e) {
            ui.printWritingToFileError(e.getMessage());
        }
    }
}