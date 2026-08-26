package nubish.utils;

/**
 * Displays Nubish messages to the user.
 */
public class UI {
    private static final String RESPONSE = """
                    _________________________________________________
                    Nubish: %s
                    _________________________________________________
                    """;

    /**
     * Prints the welcome message.
     */
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

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        System.out.printf(RESPONSE, "Bye. Hope to see you again soon!");
    }

    /**
     * Prints the numbered task list.
     */
    public void printList(String list) {
        System.out.printf(RESPONSE, list);
    }

    /**
     * Prints a message confirming a task was marked done.
     */
    public void mark(String task) {
        String replyMark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, task);

        System.out.printf(RESPONSE, replyMark);
    }

    /**
     * Prints a message confirming a task was marked not done.
     */
    public void unmark(String task) {
        String replyUnmark = String.format("""
                            I've unmarked this task:
                                %s
                            """, task);

        System.out.printf(RESPONSE, replyUnmark);
    }

    /**
     * Prints a message confirming a todo task was added.
     */
    public void todo(String input, int size) {
        String replyTodo = String.format("""
                                todo task added: %s
                            Now you have %d tasks in the list.
                            """, input, size);
        System.out.printf(RESPONSE, replyTodo);
    }

    /**
     * Prints a message confirming a deadline task was added.
     */
    public void deadline(String taskName, String deadline, int size) {
        String replyDeadline = String.format("""
                                Added task: %s (by: %s)
                            Now you have %d tasks in the list
                            """, taskName, deadline, size);
        System.out.printf(RESPONSE, replyDeadline);
    }

    /**
     * Prints a message confirming an event task was added.
     */
    public void event(String eventName, String fromTime, String toTime, int size) {
        String replyEvent = String.format("""
                                Added event: %s (From: %s, To: %s)
                            Now you have %d tasks in the list
                            """, eventName, fromTime, toTime, size);
        System.out.printf(RESPONSE, replyEvent);
    }

    /**
     * Prints a message confirming a task was deleted.
     */
    public void delete(String task, int size) {
        String replyDelete = String.format("""
                            Ok. I have removed this task:
                                %s
                            Now you have %d tasks in the list
                            """, task, size);

        System.out.printf(RESPONSE, replyDelete);
    }

    /**
     * Prints the supported command list.
     */
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
                        """;
        System.out.printf(RESPONSE, cmd);
    }
}
