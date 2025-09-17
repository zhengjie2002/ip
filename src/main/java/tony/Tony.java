package tony;

import tony.command.Command;
import tony.command.Parser;
import tony.exceptions.NoDeadlineException;
import tony.exceptions.NoDescriptionException;
import tony.task.Deadline;
import tony.task.Event;
import tony.task.Task;
import tony.task.TaskManager;
import tony.task.Todo;
import tony.ui.Ui;
import tony.exceptions.NoEventStartException;
import tony.exceptions.NoEventEndException;
import tony.file.FileUtils;
import tony.task.TaskSerializer;

import java.io.IOException;
import java.util.Scanner;

public class Tony {

    private static final String FILE_PATH = "./data/tony.txt";

    private static boolean executeCommand(Command command, TaskManager taskManager, Ui ui, FileUtils fileUtils, TaskSerializer taskSerializer) {
        try {
            switch (command.getType()) {
            case EXIT:
                return true;
            case LIST:
                ui.listAllTasks(taskManager.getTasks());
                break;
            case ADD_TODO:
                Task newTask = new Todo(command.getDescription());
                taskManager.addTask(newTask);
                try {
                    fileUtils.appendToFile(taskSerializer.serializeTask(newTask));
                } catch (IOException e) {
                    ui.printErrorMessage("Error writing to file: " + e.getMessage());
                }
                ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
                break;
            case ADD_DEADLINE:
                Task newDeadlineTask = new Deadline(command.getDescription(), command.getBy());
                taskManager.addTask(newDeadlineTask);
                try {
                    fileUtils.appendToFile(taskSerializer.serializeTask(newDeadlineTask));
                } catch (IOException e) {
                    ui.printErrorMessage("Error writing to file: " + e.getMessage());
                }
                ui.printTaskAddedMessage(newDeadlineTask, taskManager.getTaskCount());
                break;
            case ADD_EVENT:
                Task newEventTask = new Event(command.getDescription(), command.getFrom(), command.getTo());
                taskManager.addTask(newEventTask);
                try {
                    fileUtils.appendToFile(taskSerializer.serializeTask(newEventTask));
                } catch (IOException e) {
                    ui.printErrorMessage("Error writing to file: " + e.getMessage());
                }
                ui.printTaskAddedMessage(newEventTask, taskManager.getTaskCount());
                break;
            case DELETE:
                Task removedTask = taskManager.removeTask(command.getTaskIndex());
                ui.printDeleteAcknowledgement(removedTask, taskManager.getTaskCount());
                break;
            case MARK:
                taskManager.markTaskDone(command.getTaskIndex());
                break;
            case UNMARK:
                taskManager.markTaskUndone(command.getTaskIndex());
                break;
            default:
                ui.printUnknownCommandMessage();
                break;
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printErrorMessage("No such task in the list");
        }
        return false;
    }


    public static void main(String[] args) {
        boolean isExit = false;
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        Ui ui = new Ui();
        FileUtils fileUtils = new FileUtils(FILE_PATH);
        TaskSerializer taskSerializer = new TaskSerializer();

        try {
            fileUtils.loadFileContent(taskManager);
        } catch (Exception e) {
            ui.printErrorMessage("Error loading file: " + e.getMessage());
        }

        ui.printWelcome();

        while (!isExit) {
            String userInput = ui.readCommand(in);
            Command command;
            try {
                command = Parser.parseCommand(userInput);
            } catch (NoDescriptionException e) {
                ui.printErrorMessage("The description of a task cannot be empty.");
                continue;
            } catch (NoDeadlineException e) {
                ui.printErrorMessage("The deadline of a task cannot be empty. Use the format: deadline <description> /by <deadline>");
                continue;
            } catch (NoEventStartException e) {
                ui.printErrorMessage("The start time of an event cannot be empty. Use the format: event <description> /from <start time> /to <end time>");
                continue;
            } catch (NoEventEndException e) {
                ui.printErrorMessage("The end time of an event cannot be empty. Use the format: event <description> /from <start time> /to <end time>");
                continue;
            } catch (NumberFormatException e) {
                ui.printErrorMessage("Please provide a valid task number. Use the format: mark <task number> or unmark <task number>");
                continue;
            }
            isExit = executeCommand(command, taskManager, ui, fileUtils, taskSerializer);
        }
        ui.printGoodbye();
    }
}