import java.util.Scanner;

public class Nubish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int numOfTasks = 0;

        String logo = """
              _   _ _   _ ____ ___ ____  _   _ 
             | \\ | | | | | __ )_ _/ ___|| | | |
             |  \\| | | | |  _ \\| |\\___ \\| |_| |
             | |\\  | |_| | |_) | | ___) |  _  |
             |_| \\_|\\___/|____/___|____/|_| |_|
            """;
        System.out.println(logo + "Hello! I'm Nubish.\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            String response = """
                        _________________________________________________
                        Nubish: %s
                        _________________________________________________
                        """;

            if (input.equals("bye")) {
                System.out.printf(response, "Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int count = 0;
                StringBuilder list = new StringBuilder("\n");

                for (int i = 0; i < numOfTasks; i++) {
                    list.append(String.format("%d. %s\n", i + 1, tasks[i]));
                }

                System.out.printf(response, list);
            } else {
                System.out.printf(response, String.format("task added: %s", input));
                tasks[numOfTasks] = input;
                numOfTasks++;
            }
        }
    }
}