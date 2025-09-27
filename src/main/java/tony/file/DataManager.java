package tony.file;

import tony.command.Command;
import tony.command.CommandType;

import tony.exceptions.FileDataCorruptedException;

import tony.task.Deadline;
import tony.task.Event;
import tony.task.Task;
import tony.task.TaskManager;
import tony.task.TaskSerializer;
import tony.task.Todo;
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
            Command newCommand;

            switch (commandWord) {
            case "todo":
                newCommand = new Command(CommandType.ADD_TODO, words[2], isDone);
                break;
            case "deadline":
                newCommand = new Command(CommandType.ADD_DEADLINE, words[2], words[3], isDone);
                break;
            case "event":
                newCommand = new Command(CommandType.ADD_EVENT, words[2], words[3], words[4], isDone);
                break;
            default:
                throw new FileDataCorruptedException();
            }
            addTask(newCommand, taskManager);
        }
    }

    private void addTask(Command command, TaskManager taskManager) {
        switch (command.getType()) {
        case ADD_TODO:
            Task newTask = new Todo(command.getDescription());
            taskManager.addTask(newTask);
            if (command.isDone()) {
                newTask.markDone();
            }
            break;
        case ADD_DEADLINE:
            Task newDeadlineTask = new Deadline(command.getDescription(), command.getBy());
            taskManager.addTask(newDeadlineTask);
            if (command.isDone()) {
                newDeadlineTask.markDone();
            }
            break;
        case ADD_EVENT:
            Task newEventTask = new Event(command.getDescription(), command.getFrom(), command.getTo());
            taskManager.addTask(newEventTask);
            if (command.isDone()) {
                newEventTask.markDone();
            }
            break;
        default:
            break;
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