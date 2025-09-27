package tony.ui;

import java.util.ArrayList;
import java.util.Scanner;

import tony.task.Task;

public class Ui {
    public void printWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tony");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void listAllTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the Tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task);
            index++;
        }
        System.out.println("____________________________________________________________");
    }

    public void printTaskAddedMessage(Task newTask, int index) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + index + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printUnknownCommandMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Sorry, I don't understand that command.");
        System.out.println("____________________________________________________________");
    }

    public void printErrorMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("Error: " + message);
        System.out.println("____________________________________________________________");
    }

    public void printDeleteAcknowledgement(Task removedTask, int newTaskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + newTaskCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printMarkAcknowledgement(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    public void printUnmarkAcknowledgement(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    public void printMissingDescriptionError() {
        System.out.println("____________________________________________________________");
        System.out.println("Error: The description of a task cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void printMissingDeadlineError() {
        System.out.println("____________________________________________________________");
        System.out.println(
                "Error: The deadline of a task cannot be empty. Use the format: deadline <description> /by <deadline>");
        System.out.println("____________________________________________________________");
    }

    public void printMissingStartTimeError() {
        System.out.println("____________________________________________________________");
        System.out.println(
                "Error: The start time of an event cannot be empty. Use the format: event <description> /from <start time> /to <end time>");
        System.out.println("____________________________________________________________");
    }

    public void printMissingEndTimeError() {
        System.out.println("____________________________________________________________");
        System.out.println(
                "Error: The end time of an event cannot be empty. Use the format: event <description> /from <start time> /to <end time>");
        System.out.println("____________________________________________________________");
    }

    public void printInvalidTaskError() {
        System.out.println("____________________________________________________________");
        System.out.println(
                "Error: Please provide a valid task number. Use the format: mark <task number> or unmark <task number> or delete <task number>");
        System.out.println("____________________________________________________________");
    }

    public void printTaskNotFoundError() {
        System.out.println("____________________________________________________________");
        System.out.println("Error: No such task in the list.");
        System.out.println("____________________________________________________________");
    }

    public String readCommand(Scanner in) {
        return in.nextLine();
    }

}
