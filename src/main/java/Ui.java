import java.util.Scanner;

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

    public void listAllTasks(Task[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the Tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
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

    public String readCommand(Scanner in) {
        return in.nextLine();
    }
}
