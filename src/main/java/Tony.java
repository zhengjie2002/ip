import java.util.Scanner;

public class Tony {
    public static void main(String[] args) {
        String userInput = "";
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tony");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}