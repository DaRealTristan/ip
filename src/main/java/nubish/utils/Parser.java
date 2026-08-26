package nubish.utils;

import nubish.tasks.Deadline;
import nubish.tasks.Event;
import nubish.tasks.Task;
import nubish.tasks.Todo;

import java.time.format.DateTimeParseException;

public class Parser {
    Storage storage;
    UI ui;
    TaskList taskList;

    public Parser(Storage storage, UI ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    public boolean parse(String input) {
        String[] parts = input.split(" ", 2);
        Command command = Command.fromKeyword(parts[0]);
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (command) {
            case BYE:
                ui.bye();
                return false;
            case LIST:
                StringBuilder list = new StringBuilder("\n");

                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    list.append(String.format("%d. %s\n", i + 1, t.toString()));
                }

                ui.printList(list.toString());
                break;
            case MARK:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... Please put a valid task number.");
                    }
                    int indexMark = Integer.parseInt(arguments.trim()) - 1;
                    Task tMark = taskList.get(indexMark);
                    tMark.markAsDone();
                    ui.mark(tMark.toString());
                } catch (NubishException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case UNMARK:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... Please put a valid task number.");
                    }
                    int indexUnmark = Integer.parseInt(arguments.trim()) - 1;
                    Task tUnmark = taskList.get(indexUnmark);
                    tUnmark.unmarkAsDone();
                    ui.unmark(tUnmark.toString());
                } catch (NubishException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case TODO:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                    }
                    taskList.add(new Todo(arguments));
                    ui.todo(input, taskList.size());
                } catch (NubishException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    int byIndex = arguments.indexOf(ArgumentToken.BY.getToken());
                    if (byIndex == -1) {
                        throw new NubishException("Hrmmm... Please use the proper format for deadlines: " +
                                "deadline {taskname} /by {deadline}");
                    }
                    String taskName = arguments.substring(0, byIndex).trim();
                    String deadline = arguments.substring(byIndex + ArgumentToken.BY.getToken().length()).trim();
                    if (taskName.isEmpty()) {
                        throw new NubishException("Hrmmm... The description of a deadline cannot be empty.");
                    }
                    if (deadline.isEmpty()) {
                        throw new NubishException("Hrmmm... The deadline of the task cannot be empty.");
                    }
                    taskList.add(new Deadline(taskName, deadline));
                    ui.deadline(taskName, deadline, taskList.size());
                } catch (NubishException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Hrmmm... Please use the proper format for datetimes: dd/MM/yyyyy hhmm");
                }

                break;
            case EVENT:
                try {
                    int fromIndex = arguments.indexOf(ArgumentToken.FROM.getToken());
                    int toIndex = arguments.indexOf(ArgumentToken.TO.getToken());

                    if (fromIndex == -1 || toIndex == -1) {
                        throw new NubishException("Hrmmm... Please use the proper format for events: " +
                                "event {eventName} /from {startDate} /to {enddate}");
                    }

                    String eventName = arguments.substring(0, fromIndex).trim();
                    String fromTime = arguments.substring(
                            fromIndex + ArgumentToken.FROM.getToken().length(), toIndex).trim();
                    String toTime = arguments.substring(toIndex + ArgumentToken.TO.getToken().length()).trim();

                    if (eventName.isEmpty()) {
                        throw new NubishException("Hrmmm... The name of an event cannot be empty.");
                    }
                    if (fromTime.isEmpty()) {
                        throw new NubishException("Hrmmm... The start of an event cannot be empty.");
                    }
                    if (toTime.isEmpty()) {
                        throw new NubishException("Hrmmm... The end of an event cannot be empty.");
                    }
                    taskList.add(new Event(eventName, fromTime, toTime));
                    ui.event(eventName, fromTime, toTime, taskList.size());
                } catch (NubishException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Hrmmm... Please use the proper format for datetimes: dd/MM/yyyyy hhmm");
                }

                break;
            case DELETE:
                try {
                    if (arguments.isEmpty()) {
                        throw new NubishException("Hrmmm... The description of a todo cannot be empty.");
                    }

                    int indexDelete = Integer.parseInt(arguments.trim()) - 1;

                    Task t = taskList.remove(indexDelete);
                    ui.delete(t.toString(), taskList.size());
                } catch (NubishException e) {
                    System.out.printf(e.getMessage());
                }
                break;
            default:
                ui.commandList();
        }
        return true;
    }

}
