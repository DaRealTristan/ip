package nubish.utils;

/**
 * Handles all messages printed to the user.
 */
public class UI {
    private static final String RESPONSE = """
                    _________________________________________________
                    Nubish: %s
                    _________________________________________________
                    """;

    /**
     * Prints the greeting message shown when Nubish starts.
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
     * Prints the farewell message shown when Nubish exits.
     */
    public void bye() {
        System.out.printf(RESPONSE, "Bye. Hope to see you again soon!");
    }

    /**
     * Prints the formatted task list.
     *
     * @param list formatted list of tasks
     */
    public void printList(String list) {
        System.out.printf(RESPONSE, list);
    }

    /**
     * Prints a message confirming that a task was marked as done.
     *
     * @param task formatted task that was marked
     */
    public void mark(String task) {
        String replyMark = String.format("""
                            Nice! I've marked this task as done:
                                %s
                            """, task);

        System.out.printf(RESPONSE, replyMark);
    }

    /**
     * Prints a message confirming that a task was marked as not done.
     *
     * @param task formatted task that was unmarked
     */
    public void unmark(String task) {
        String replyUnmark = String.format("""
                            I've unmarked this task:
                                %s
                            """, task);

        System.out.printf(RESPONSE, replyUnmark);
    }

    /**
     * Prints a message confirming that a todo task was added.
     *
     * @param input original todo command entered by the user
     * @param size number of tasks after adding the todo
     */
    public void todo(String input, int size) {
        String replyTodo = String.format("""
                                todo task added: %s
                            Now you have %d tasks in the list.
                            """, input, size);
        System.out.printf(RESPONSE, replyTodo);
    }

    /**
     * Prints a message confirming that a deadline task was added.
     *
     * @param taskName description of the deadline task
     * @param deadline deadline entered by the user
     * @param size number of tasks after adding the deadline
     */
    public void deadline(String taskName, String deadline, int size) {
        String replyDeadline = String.format("""
                                Added task: %s (by: %s)
                            Now you have %d tasks in the list
                            """, taskName, deadline, size);
        System.out.printf(RESPONSE, replyDeadline);
    }

    /**
     * Prints a message confirming that an event task was added.
     *
     * @param eventName description of the event task
     * @param fromTime start date and time entered by the user
     * @param toTime end date and time entered by the user
     * @param size number of tasks after adding the event
     */
    public void event(String eventName, String fromTime, String toTime, int size) {
        String replyEvent = String.format("""
                                Added event: %s (From: %s, To: %s)
                            Now you have %d tasks in the list
                            """, eventName, fromTime, toTime, size);
        System.out.printf(RESPONSE, replyEvent);
    }

    /**
     * Prints a message confirming that a task was deleted.
     *
     * @param task formatted task that was deleted
     * @param size number of tasks after deletion
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
     * Prints the list of commands currently supported by Nubish.
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
                        - find
                        """;
        System.out.printf(RESPONSE, cmd);
    }

    /**
     * Prints the list of tasks whose descriptions matched the search keyword.
     *
     * @param foundList formatted list of matching tasks
     */
    public void find(String foundList) {
        String found = String.format("""
                    Here are the matching tasks in your list:
                    %s
                """, foundList);
        System.out.printf(RESPONSE, found);
    }
}
