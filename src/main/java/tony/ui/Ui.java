package tony.ui;

import java.util.ArrayList;
import java.util.Scanner;

import tony.task.Task;

/**
 * The `Ui` class handles all user interactions, including displaying messages
 * and reading user input. It provides methods to display various messages
 * related to tasks and errors.
 */
public class Ui {

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm Tony");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void listAllTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the Tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task);
            index++;
        }
        printLine();
    }

    public void listMatchingTasks(ArrayList<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index + "." + task);
                index++;
            }
        }
        printLine();
    }

    public void printTaskAddedMessage(Task newTask, int index) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + index + " task(s) in the list.");
        printLine();
    }

    public void printUnknownCommandMessage() {
        printLine();
        System.out.println("Sorry, I don't understand that command.");
        printLine();
    }

    public void printDeleteAcknowledgement(Task removedTask, int newTaskCount) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + newTaskCount + " task(s) in the list.");
        printLine();
    }

    public void printMarkAcknowledgement(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        printLine();
    }

    public void printUnmarkAcknowledgement(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
        printLine();
    }

    public void printMissingDescriptionError() {
        printLine();
        System.out.println("Error: The description of a task cannot be empty.");
        printLine();
    }

    public void printMissingDeadlineError() {
        printLine();
        System.out.println(
                "Error: The deadline of a task cannot be empty. Use the format: deadline <description> /by <deadline>");
        printLine();
    }

    public void printMissingStartTimeError() {
        printLine();
        System.out.println(
                "Error: The start time of an event cannot be empty. Use the format: event <description> /from <start time> /to <end time>");
        printLine();
    }

    public void printMissingEndTimeError() {
        printLine();
        System.out.println(
                "Error: The end time of an event cannot be empty. Use the format: event <description> /from <start time> /to <end time>");
        printLine();
    }

    public void printInvalidTaskError() {
        printLine();
        System.out.println(
                "Error: Please provide a valid task number. Use the format: mark <task number> or unmark <task number> or delete <task number>");
        printLine();
    }

    public void printTaskNotFoundError() {
        printLine();
        System.out.println("Error: No such task in the list.");
        printLine();
    }

    public void printReadFileError(String message) {
        printLine();
        System.out.println("Error reading file: " + message);
        printLine();
    }

    public void printCreateFileError(String message) {
        printLine();
        System.out.println("Error creating file: " + message);
        printLine();
    }

    public void printFileCorruptedError() {
        printLine();
        System.out.println("Error: File data is corrupted.");
        printLine();
    }

    public void printWritingToFileError(String message) {
        printLine();
        System.out.println("Error writing to file: " + message);
        printLine();
    }

    public void printMissingSearchKeywordError() {
        printLine();
        System.out.println("Error: The search keyword cannot be empty. Use the format: find <keyword>");
        printLine();
    }

    public void printInvalidDateFormatError() {
        printLine();
        System.out.println("Error: The date format is invalid. Please use the format DD-MM-YYYY.");
        printLine();
    }

    public void printError(String message) {
        printLine();
        System.out.println("Error: " + message);
        printLine();
    }

    public void printTaskAlreadyMarkedError() {
        printLine();
        System.out.println("Error: This task is already marked as done/undone. No action has been taken.");
        printLine();
    }

    public void printEventEndBeforeStartError() {
        printLine();
        System.out.println("Error: The end date of an event cannot be before the start time.");
        printLine();
    }

    public void printInvalidEventFormatError() {
        printLine();
        System.out.println(
                "Error: The event format is invalid. Please use the format: event <description> /from <start time> /to <end time>");
        printLine();
    }

    public String readCommand(Scanner in) {
        return in.nextLine();
    }
}
