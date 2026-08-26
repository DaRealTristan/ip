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
     * Creates an incomplete deadline task with the given description and deadline.
     *
     * @param description description of the deadline task
     * @param deadline deadline in {@code dd/MM/yyyy HHmm} format
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = convertToDateTime(deadline);
    }

    /**
     * Creates a deadline task with the given completion status, description, and deadline.
     *
     * @param isDone whether the deadline task has been completed
     * @param description description of the deadline task
     * @param deadline deadline in {@code dd/MM/yyyy HHmm} format
     */
    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = convertToDateTime(deadline);
    }

    /**
     * Converts a saved deadline string into a {@link LocalDateTime}.
     *
     * @param dateTimeString date and time in {@code dd/MM/yyyy HHmm} format
     * @return parsed date and time
     */
    private LocalDateTime convertToDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, SAVE_FORMAT);
    }

    /**
     * Returns the user-facing representation of this deadline task.
     *
     * @return formatted deadline string with type, status, description, and deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(PRINT_FORMAT));
    }

    /**
     * Returns the storage representation of this deadline task.
     *
     * @return formatted deadline string suitable for saving to disk
     */
    @Override
    public String saveString() {
        return String.format("D | %s | %s", super.saveString(), this.deadline.format(SAVE_FORMAT));
    }
}
