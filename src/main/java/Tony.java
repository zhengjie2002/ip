import java.util.Scanner;

public class Tony {
    private static void listTasks(Task[] tasks) {
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

    public static void main(String[] args) {
        String userInput = "";
        int index = 0;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tony");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                listTasks(tasks);
            } else if (userInput.startsWith("todo")) {
                Todo newTodo = new Todo(userInput);
                tasks[index] = newTodo;
                index++;
            } else if (userInput.startsWith("mark")) {
                char i = userInput.charAt(5);
                tasks[Character.getNumericValue(i) - 1].markDone();
            } else if (userInput.startsWith("unmark")) {
                char i = userInput.charAt(7);
                tasks[Character.getNumericValue(i) - 1].unmarkDone();
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