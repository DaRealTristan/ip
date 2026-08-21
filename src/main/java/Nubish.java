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
                case "todo":
                    try {
                        if (arguments.isEmpty()) {
                            throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                        }
                        System.out.printf(response, String.format("""
                            todo task added: %s
                        Now you have %d tasks in the list.
                        """, input, numOfTasks + 1));
                        tasks[numOfTasks] = new Todo(arguments);
                        numOfTasks++;
                    }
                    catch (NubishException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        int byIndex = arguments.indexOf("/by");
                        if (byIndex == -1) {
                            throw new NubishException("Hrmmm... Please use the proper format for deadlines: " +
                                    "deadline {taskname} /by {deadline}");
                        }
                        String taskName = arguments.substring(0, byIndex).trim();
                        String deadline = arguments.substring(byIndex + 3).trim();
                        if (taskName.isEmpty()) {
                            throw new NubishException("Hrmmm... The description of a deadline cannot be empty.");
                        }
                        if (deadline.isEmpty()) {
                            throw new NubishException("Hrmmm... The deadline of the task cannot be empty.");
                        }
                        String replyDeadline = String.format("""
                                Added task: %s (by: %s)
                            Now you have %d tasks in the list
                            """, taskName, deadline, numOfTasks + 1);
                        System.out.printf(response, replyDeadline);
                        tasks[numOfTasks] = new Deadline(taskName, deadline);
                        numOfTasks++;
                    }
                    catch (NubishException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "event":
                    try {
                        int fromIndex = arguments.indexOf("/from");
                        int toIndex = arguments.indexOf("/to");

                        if (fromIndex == -1 || toIndex == -1) {
                            throw new NubishException("Hrmmm... Please use the proper format for events: " +
                                    "event {eventName} /from {startDate} /to {enddate}");
                        }

                        String eventName = arguments.substring(0, fromIndex).trim();
                        String fromTime = arguments.substring(fromIndex + 5, toIndex).trim();
                        String toTime = arguments.substring(toIndex + 3).trim();

                        if (eventName.isEmpty()) {
                            throw new NubishException("Hrmmm... The name of an event cannot be empty.");
                        }
                        if (fromTime.isEmpty()) {
                            throw new NubishException("Hrmmm... The start of an event cannot be empty.");
                        }
                        if (toTime.isEmpty()) {
                            throw new NubishException("Hrmmm... The end of an event cannot be empty.");
                        }

                        String replyEvent = String.format("""
                                Added event: %s (From: %s, To: %s)
                            Now you have %d tasks in the list
                            """, eventName, fromTime, toTime, numOfTasks + 1);
                        System.out.printf(response, replyEvent);
                        tasks[numOfTasks] = new Event(eventName, fromTime, toTime);
                        numOfTasks++;
                    }
                    catch (NubishException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    System.out.printf(response, """
                            OOPS!!! I'm sorry, but I don't know what that means :-(
                            Here is a list of the current commands:
                            - todo
                            - deadline
                            - event
                            - mark
                            - unmark
                            """);
            }
        }
    }
}