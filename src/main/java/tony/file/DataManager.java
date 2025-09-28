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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages the loading, saving, and updating of task data to and from a file.
 * The <code>DataManager</code> class handles file operations, including parsing
 * task data from a file and serializing tasks for storage.
 */
public class DataManager {

    /** The delimiter used to split task data in the file. */
    private static final String DELIMITER = "\\|";

    /** The date format used for parsing and formatting task dates. */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd";

    /** The file path where task data is stored. */
    private final String filePath;

    private FileUtils fileUtils;

    /** Serializer for converting tasks to string representations. */
    private final TaskSerializer taskSerializer = new TaskSerializer();

    /** User interface for displaying messages. */
    private final Ui ui = new Ui();

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the file and populates the TaskManager.
     * If the file data is corrupted, an error message is displayed, and all tasks are cleared.
     *
     * @param taskManager The TaskManager to populate with loaded tasks.
     */
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

    /**
     * Parses the task data from the file and adds the tasks to the TaskManager.
     *
     * @param dataStrings The list of strings representing task data.
     * @param taskManager The TaskManager to populate with parsed tasks.
     */
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
                LocalDate deadlineDate = convertStringToLocalDate(words[3]);
                addFromFileCommand = new AddDeadlineCommand(words[2], deadlineDate, isDone);
                break;
            case "event":
                LocalDate fromDate = convertStringToLocalDate(words[3]);
                LocalDate toDate = convertStringToLocalDate(words[4]);
                addFromFileCommand = new AddEventCommand(words[2], fromDate, toDate, isDone);
                break;
            default:
                throw new FileDataCorruptedException();
            }
            addFromFileCommand.executeFromFile(taskManager);
        }
    }

    /**
     * Converts a string representation of a date to a LocalDate object.
     *
     * @param unformattedDateString The string representation of the date.
     * @return The parsed LocalDate object.
     * @throws FileDataCorruptedException If the date string cannot be parsed due to format issues.
     */
    private static LocalDate convertStringToLocalDate(String unformattedDateString) {
        LocalDate formattedDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
            formattedDate = LocalDate.parse(unformattedDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new FileDataCorruptedException();
        }
        return formattedDate;
    }

    /**
     * Saves a single task to the file.
     *
     * @param task The task to be saved.
     */
    public void saveTask(Task task) {
        try {
            String taskText = taskSerializer.serializeTask(task);
            fileUtils.appendToFile(taskText);
        } catch (IOException e) {
            ui.printWritingToFileError(e.getMessage());
        }
    }

    /**
     * Saves all tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    private void saveAllTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            saveTask(task);
        }
    }

    /**
     * Updates the file with the current tasks in the TaskManager.
     * Clears the file and writes all tasks to it.
     *
     * @param taskManager The TaskManager managing the tasks to be saved.
     */
    public void updateFile(TaskManager taskManager) {
        try {
            fileUtils.clearFile();
            saveAllTasks(taskManager.getTasks());
        } catch (IOException e) {
            ui.printWritingToFileError(e.getMessage());
        }
    }
}