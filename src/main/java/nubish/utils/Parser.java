package nubish.utils;

import java.time.format.DateTimeParseException;

import nubish.tasks.Deadline;
import nubish.tasks.Event;
import nubish.tasks.Task;
import nubish.tasks.Todo;

/**
 * Parses user commands and applies the requested changes to the task list.
 */
public class Parser {
    private final Storage storage;
    private final UI ui;
    private final TaskList taskList;

    /**
     * Creates a parser that coordinates storage, UI output, and task list updates.
     *
     * @param storage storage used by the application
     * @param ui user interface used to display command results
     * @param taskList task list modified by parsed commands
     */
    public Parser(Storage storage, UI ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses and executes one line of user input.
     *
     * @param input raw user command
     * @return {@code false} when the command requests exit, or {@code true} otherwise
     */
    public boolean parse(String input) {
        String[] parts = input.split(" ", 2);
        Command command = Command.fromKeyword(parts[0]);
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (command) {
            case BYE:
                ui.printBye();
                return false;
            case LIST:
                ui.printList(taskList.toString());
                break;
            case MARK:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... Please put a valid task number.");
                    }
                    int markIndex = Integer.parseInt(arguments.trim()) - 1;
                    Task taskToMark = taskList.get(markIndex);
                    taskToMark.markAsDone();
                    ui.printMark(taskToMark.toString());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case UNMARK:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... Please put a valid task number.");
                    }
                    int unmarkIndex = Integer.parseInt(arguments.trim()) - 1;
                    Task taskToUnmark = taskList.get(unmarkIndex);
                    taskToUnmark.unmarkAsDone();
                    ui.printUnmark(taskToUnmark.toString());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case TODO:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                    }
                    taskList.add(new Todo(arguments));
                    ui.printTodoAdded(input, taskList.size());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    int byIndex = arguments.indexOf(ArgumentToken.BY.getToken());
                    if (byIndex == -1) {
                        throw new NubishException("Hrmmm... Please use the proper format for deadlines: "
                                + "printDeadline {taskname} /by {printDeadline}");
                    }
                    String taskName = arguments.substring(0, byIndex).trim();
                    String deadline = arguments.substring(byIndex + ArgumentToken.BY.getToken().length()).trim();
                    if (taskName.isEmpty()) {
                        throw new NubishException("Hrmmm... The description of a printDeadline cannot be empty.");
                    }
                    if (deadline.isEmpty()) {
                        throw new NubishException("Hrmmm... The printDeadline of the task cannot be empty.");
                    }
                    taskList.add(new Deadline(taskName, deadline));
                    ui.printDeadlineAdded(taskName, deadline, taskList.size());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.showError("Hrmmm... Please use the proper format for datetimes: dd/MM/yyyyy hhmm");
                }

                break;
            case EVENT:
                try {
                    int fromIndex = arguments.indexOf(ArgumentToken.FROM.getToken());
                    int toIndex = arguments.indexOf(ArgumentToken.TO.getToken());

                    if (fromIndex == -1 || toIndex == -1) {
                        throw new NubishException("Hrmmm... Please use the proper format for events: "
                                + "printEvent {eventName} /from {startDate} /to {enddate}");
                    }

                    String eventName = arguments.substring(0, fromIndex).trim();
                    String fromTime = arguments.substring(
                            fromIndex + ArgumentToken.FROM.getToken().length(), toIndex).trim();
                    String toTime = arguments.substring(toIndex + ArgumentToken.TO.getToken().length()).trim();

                    if (eventName.isEmpty()) {
                        throw new NubishException("Hrmmm... The name of an printEvent cannot be empty.");
                    }
                    if (fromTime.isEmpty()) {
                        throw new NubishException("Hrmmm... The start of an printEvent cannot be empty.");
                    }
                    if (toTime.isEmpty()) {
                        throw new NubishException("Hrmmm... The end of an printEvent cannot be empty.");
                    }
                    taskList.add(new Event(eventName, fromTime, toTime));
                    ui.printEventAdded(eventName, fromTime, toTime, taskList.size());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.showError("Hrmmm... Please use the proper format for datetimes: dd/MM/yyyyy hhmm");
                }

                break;
            case DELETE:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                    }

                    int deleteIndex = Integer.parseInt(arguments.trim()) - 1;

                    Task task = taskList.remove(deleteIndex);
                    ui.printTaskDelete(task.toString(), taskList.size());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case FIND:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... Please enter keywords to search for");
                    }

                    TaskList foundList = taskList.find(arguments);
                    ui.find(foundList.toString());
                } catch (NubishException e) {
                    ui.showError(e.getMessage());
                }
                break;
            default:
                ui.printCommandList();
        }
        return true;
    }

}
