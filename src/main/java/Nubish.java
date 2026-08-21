import java.util.Scanner;
import java.util.ArrayList;

import utils.Task;
import utils.Deadline;
import utils.Event;
import utils.Todo;
import utils.NubishException;

public class Nubish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
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
                    StringBuilder list = new StringBuilder("\n");

                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        list.append(String.format("%d. %s\n", i + 1, t.toString()));
                    }

                    System.out.printf(response, list);
                    break;
                case "mark":
                    try {
                        if (arguments.isEmpty()) {
                            throw new NubishException("Hrmmm... Please put a valid task number.");
                        }
                        int indexMark = Integer.parseInt(arguments.trim()) - 1;
                        Task tMark = tasks.get(indexMark);
                        tMark.markAsDone();
                        String replyMark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, tMark.toString());
                        System.out.printf(response, replyMark);
                    }
                    catch (NubishException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        if (arguments.isEmpty()) {
                            throw new NubishException("Hrmmm... Please put a valid task number.");
                        }
                        int indexUnmark = Integer.parseInt(arguments.trim()) - 1;
                        Task tUnmark = tasks.get(indexUnmark);
                        tUnmark.unmarkAsDone();
                        String replyUnmark = String.format("""
                            I've unmarked this task:
                                %s
                            """, tUnmark.toString());
                        System.out.printf(response, replyUnmark);
                    }
                    catch (NubishException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        if (arguments.isEmpty()) {
                            throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                        }
                        tasks.add(new Todo(arguments));
                        System.out.printf(response, String.format("""
                            todo task added: %s
                        Now you have %d tasks in the list.
                        """, input, tasks.size()));
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
                        tasks.add(new Deadline(taskName, deadline));
                        String replyDeadline = String.format("""
                                Added task: %s (by: %s)
                            Now you have %d tasks in the list
                            """, taskName, deadline, tasks.size());
                        System.out.printf(response, replyDeadline);
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
                        tasks.add(new Event(eventName, fromTime, toTime));
                        String replyEvent = String.format("""
                                Added event: %s (From: %s, To: %s)
                            Now you have %d tasks in the list
                            """, eventName, fromTime, toTime, tasks.size());
                        System.out.printf(response, replyEvent);
                    }
                    catch (NubishException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "delete":
                    try {
                        if (arguments.isEmpty()) {
                            throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                        }

                        int indexDelete = Integer.parseInt(arguments.trim()) - 1;

                        Task t = tasks.remove(indexDelete);
                        String replyEvent = String.format("""
                            Ok. I have removed this task:
                                %s
                            Now you have %d tasks in the list
                            """, t.toString(), tasks.size());
                        System.out.printf(response, replyEvent);
                    }
                    catch (NubishException e) {
                        System.out.printf(e.getMessage());
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
                            - delete
                            """);
            }
        }
    }
}