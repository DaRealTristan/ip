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
    private String lastResponse;

    /**
     * Prints the greeting message shown when Nubish starts.
     */
    public void greet() {
        lastResponse = getGreeting();
        System.out.println(lastResponse);
    }

    /**
     * Returns the greeting message shown when Nubish starts.
     *
     * @return greeting message
     */
    public String getGreeting() {
        return """
                N   N U   U BBBB  III  SSSS H   H
                NN  N U   U B   B  I  S     H   H
                N N N U   U BBBB   I   SSS  HHHHH
                N  NN U   U B   B  I      S H   H
                N   N  UUU  BBBB  III SSSS  H   H
                Hello! I'm Nubish.
                What can I do for you?""";
    }

    /**
     * Returns the latest response produced by the UI.
     *
     * @return latest response text
     */
    public String getLastResponse() {
        return lastResponse;
    }

    /**
     * Prints the farewell message shown when Nubish exits.
     */
    public void bye() {
        printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the formatted task list.
     *
     * @param list formatted list of tasks
     */
    public void printList(String list) {
        printResponse(list);
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

        printResponse(replyMark);
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

        printResponse(replyUnmark);
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
        printResponse(replyTodo);
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
        printResponse(replyDeadline);
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
        printResponse(replyEvent);
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

        printResponse(replyDelete);
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
        printResponse(cmd);
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
        printResponse(found);
    }

    /**
     * Prints an error message from Nubish command handling.
     *
     * @param message error message to show
     */
    public void showError(String message) {
        printResponse(message);
    }

    private void printResponse(String response) {
        lastResponse = response;
        System.out.printf(RESPONSE, response);
    }
}
