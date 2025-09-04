import java.util.Scanner;

public class Tony {
    private static final int MAX_TASKS = 100;


    public static void main(String[] args) {
        String userInput = "";

        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(MAX_TASKS);

        Ui.printWelcome();

        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                Ui.listAllTasks(taskManager.getTasks());
            } else if (userInput.startsWith("todo")) {
                Task newTask = new Todo(userInput.substring(4).trim());
                taskManager.addTask(newTask);
                Ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
            } else if (userInput.startsWith("deadline")) {
                int indexOfDeadline = userInput.indexOf("/by");
                Task newDeadlineObject = new Deadline(userInput.substring(8, indexOfDeadline).trim(),
                        userInput.substring(indexOfDeadline + 3).trim());
                taskManager.addTask(newDeadlineObject);
                Ui.printTaskAddedMessage(newDeadlineObject, taskManager.getTaskCount());
            } else if (userInput.startsWith("event")) {
                int indexOfEventFrom = userInput.indexOf("/from");
                int indexOfEventTo = userInput.indexOf("/to");
                Task newEventObject = new Event(userInput.substring(5, indexOfEventFrom).trim(),
                        userInput.substring(indexOfEventFrom + 5, indexOfEventTo).trim(),
                        userInput.substring(indexOfEventTo + 3).trim());
                taskManager.addTask(newEventObject);
                Ui.printTaskAddedMessage(newEventObject, taskManager.getTaskCount());
            } else if (userInput.startsWith("mark")) {
                char i = userInput.charAt(5);
                taskManager.markTaskDone(Character.getNumericValue(i) - 1);
            } else if (userInput.startsWith("unmark")) {
                char i = userInput.charAt(7);
                taskManager.markTaskUndone(Character.getNumericValue(i) - 1);
            } else {
                System.out.println("Sorry, I don't understand that command.");
            }
        }

        Ui.printGoodbye();
    }

}