package nubish.tasks;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String saveString() {
        return String.format("T | %s", super.saveString());
    }
}