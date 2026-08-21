import java.util.Scanner;
import utils.Task;

public class Nubish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        String logo = """
              _   _ _   _ ____ ___ ____  _   _ 
             | \\ | | | | | __ )_ _/ ___|| | | |
             |  \\| | | | |  _ \\| |\\___ \\| |_| |
             | |\\  | |_| | |_) | | ___) |  _  |
             |_| \\_|\\___/|____/___|____/|_| |_|
            """;
        System.out.println(logo + "Hello! I'm Nubish.\nWhat can I do for you?");

        programmeLoop:
        while (true) {
            String input = scanner.nextLine();
            String response = """
                    _________________________________________________
                    Nubish: %s
                    _________________________________________________
                    """;

            switch (input) {
                case "bye":
                    System.out.printf(response, "Bye. Hope to see you again soon!");
                    break programmeLoop;
                case "list":
                    int count = 0;
                    StringBuilder list = new StringBuilder("\n");

                    for (int i = 0; i < numOfTasks; i++) {
                        Task t = tasks[i];
                        list.append(String.format("%d. %s\n", i + 1, t.toString()));
                    }

                    System.out.printf(response, list);
                    break;
                case String s when s.startsWith("mark "):
                    int indexMark = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                    Task tMark = tasks[indexMark];
                    tMark.markAsDone();
                    String replyMark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, tMark.toString());
                    System.out.printf(response, replyMark);
                    break;
                case String s when s.startsWith("unmark "):
                    int indexUnmark = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                    Task tUnmark = tasks[indexUnmark];
                    tUnmark.unmarkAsDone();
                    String replyUnmark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, tUnmark.toString());
                    System.out.printf(response, replyUnmark);
                    break;
                default:
                    System.out.printf(response, String.format("task added: %s", input));
                    tasks[numOfTasks] = new Task(input);
                    numOfTasks++;
                    break;
            }
        }
    }
}