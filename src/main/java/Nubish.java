import java.util.Scanner;
import utils.Task;
import utils.Deadline;
import utils.Event;
import utils.Todo;
import utils.NubishException;

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
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String arguments = (parts.length > 1) ? parts[1] : "";

            String response = """
                    _________________________________________________
                    Nubish: %s
                    _________________________________________________
                    """;

            switch (command) {
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
                case "mark":
                    int indexMark = Integer.parseInt(arguments.trim()) - 1;
                    Task tMark = tasks[indexMark];
                    tMark.markAsDone();
                    String replyMark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, tMark.toString());
                    System.out.printf(response, replyMark);
                    break;
                case "unmark":
                    int indexUnmark = Integer.parseInt(arguments.trim()) - 1;
                    Task tUnmark = tasks[indexUnmark];
                    tUnmark.unmarkAsDone();
                    String replyUnmark = String.format("""
                            I've unmarked this task:
                                %s
                            """, tUnmark.toString());
                    System.out.printf(response, replyUnmark);
                    break;
                case "deadline":
                    int byIndex = arguments.indexOf("/by");
                    String taskName = arguments.substring(0, byIndex).trim();
                    String deadline = arguments.substring(byIndex + 3).trim();
                    String replyDeadline = String.format("""
                                Added task: %s (by: %s)
                            Now you have %d tasks in the list
                            """, taskName, deadline, numOfTasks + 1);
                    System.out.printf(response, replyDeadline);
                    tasks[numOfTasks] = new Deadline(taskName, deadline);
                    numOfTasks++;
                    break;
                case "event":
                    int fromIndex = arguments.indexOf("/from");
                    int toIndex = arguments.indexOf("/to");

                    String eventName = arguments.substring(0, fromIndex).trim();
                    String fromTime = arguments.substring(fromIndex + 5, toIndex).trim();
                    String toTime = arguments.substring(toIndex + 3).trim();

                    String replyEvent = String.format("""
                                Added event: %s (From: %s, To: %s)
                            Now you have %d tasks in the list
                            """, eventName, fromTime, toTime, numOfTasks + 1);
                    System.out.printf(response, replyEvent);
                    tasks[numOfTasks] = new Event(eventName, fromTime, toTime);
                    numOfTasks++;
                    break;
                default:
                    System.out.printf(response, String.format("""
                            task added: %s
                        Now you have %d tasks in the list.
                        """, input, numOfTasks + 1));
                    tasks[numOfTasks] = new Todo(input);
                    numOfTasks++;
                    break;
            }
        }
    }
}