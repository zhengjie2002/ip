import java.util.Scanner;

public class Tony {
    private static void listTasks(Task[] tasks) {
        int index = 0;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(index + ". " + task.getDescription());
            index++;
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String userInput = "";
        Task[] tasks = new Task[100];
        int index = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tony");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            if (userInput.equals("list")) {
                listTasks(tasks);
            } else {
                Task newTask = new Task(userInput);
                tasks[index] = newTask;
                index++;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}