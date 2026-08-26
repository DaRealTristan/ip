package nubish.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that happens between two specific date-time values.
 */
public class Event extends Task {
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("HHmm MMM d yyyy");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an incomplete event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = convertToDateTime(from);
        this.to = convertToDateTime(to);
    }

    /**
     * Creates an event task with the specified completion status.
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = convertToDateTime(from);
        this.to = convertToDateTime(to);
    }

    /**
     * Returns the parsed date-time represented by the storage date-time format.
     */
    public LocalDateTime convertToDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, SAVE_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(PRINT_FORMAT),
                this.to.format(PRINT_FORMAT));
    }

    @Override
    public String saveString() {
        return String.format("E | %s | %s | %s", super.saveString(), this.from.format(SAVE_FORMAT),
                this.to.format(SAVE_FORMAT));
    }
}
