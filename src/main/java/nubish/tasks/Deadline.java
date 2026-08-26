package nubish.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("HHmm MMM d yyyy");

    private LocalDateTime deadline;

    /**
     * Creates an incomplete deadline task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = convertToDateTime(deadline);
    }

    /**
     * Creates a deadline task with the specified completion status.
     */
    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = convertToDateTime(deadline);
    }

    private LocalDateTime convertToDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, SAVE_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(PRINT_FORMAT));
    }

    @Override
    public String saveString() {
        return String.format("D | %s | %s", super.saveString(), this.deadline.format(SAVE_FORMAT));
    }
}
