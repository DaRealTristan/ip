package nubish.tasks;

/**
 * Represents a task without a deadline or event time.
 */
public class Todo extends Task {
    /**
     * Creates an incomplete todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo task with the specified completion status.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String saveString() {
        return String.format("T | %s", super.saveString());
    }
}
