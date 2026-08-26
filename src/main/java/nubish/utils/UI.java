package nubish.utils;

public class UI {
    private String response = """
                    _________________________________________________
                    Nubish: %s
                    _________________________________________________
                    """;

    public void greet() {
        String logo = """
              _   _ _   _ ____ ___ ____  _   _ 
             | \\ | | | | | __ )_ _/ ___|| | | |
             |  \\| | | | |  _ \\| |\\___ \\| |_| |
             | |\\  | |_| | |_) | | ___) |  _  |
             |_| \\_|\\___/|____/___|____/|_| |_|
            """;
        System.out.println(logo + "Hello! I'm Nubish.\nWhat can I do for you?");
    }

    public void bye() {
        System.out.printf(response, "Bye. Hope to see you again soon!");
    }

    public void printList(String list) {
        System.out.printf(response, list);
    }

    public void mark(String task) {
        String replyMark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, task);

        System.out.printf(response, replyMark);
    }

    public void unmark(String task) {
        String replyUnmark = String.format("""
                            I've unmarked this task:
                                %s
                            """, task);

        System.out.printf(response, replyUnmark);
    }

    public void todo(String input, int size) {
        String replyTodo = String.format("""
                                todo task added: %s
                            Now you have %d tasks in the list.
                            """, input, size);
        System.out.printf(response, replyTodo);
    }

    public void deadline(String taskName, String deadline, int size) {
        String replyDeadline = String.format("""
                                Added task: %s (by: %s)
                            Now you have %d tasks in the list
                            """, taskName, deadline, size);
        System.out.printf(response, replyDeadline);
    }

    public void event(String eventName, String fromTime, String toTime, int size) {
        String replyEvent = String.format("""
                                Added event: %s (From: %s, To: %s)
                            Now you have %d tasks in the list
                            """, eventName, fromTime, toTime, size);
        System.out.printf(response, replyEvent);
    }

    public void delete(String task, int size) {
        String replyDelete = String.format("""
                            Ok. I have removed this task:
                                %s
                            Now you have %d tasks in the list
                            """, task, size);

        System.out.printf(response, replyDelete);
    }

    public void commandList() {
        String cmd = """
                        OOPS!!! I'm sorry, but I don't know what that means :-(
                        Here is a list of the current commands:
                        - todo
                        - deadline
                        - event
                        - mark
                        - unmark
                        - delete
                        - find
                        """;
        System.out.printf(response, cmd);
    }

    public void find(String foundList) {
        String found = String.format("""
                    Here are the matching tasks in your list:
                    %s
                """, foundList);
        System.out.printf(response, found);
    }
}
