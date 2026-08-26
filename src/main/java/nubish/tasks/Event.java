package nubish.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that happens between a start and end date and time.
 */
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("HHmm MMM d yyyy");

    /**
     * Creates an incomplete event task with the given description and time range.
     *
     * @param description description of the event task
     * @param from start date and time in {@code dd/MM/yyyy HHmm} format
     * @param to end date and time in {@code dd/MM/yyyy HHmm} format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = convertToDateTime(from);
        this.to = convertToDateTime(to);
    }

    /**
     * Creates an event task with the given completion status, description, and time range.
     *
     * @param isDone whether the event task has been completed
     * @param description description of the event task
     * @param from start date and time in {@code dd/MM/yyyy HHmm} format
     * @param to end date and time in {@code dd/MM/yyyy HHmm} format
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = convertToDateTime(from);
        this.to = convertToDateTime(to);
    }

    /**
     * Converts a saved event date-time string into a {@link LocalDateTime}.
     *
     * @param dateTimeString date and time in {@code dd/MM/yyyy HHmm} format
     * @return parsed date and time
     */
    public LocalDateTime convertToDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, saveFormat);
    }

    /**
     * Returns the user-facing representation of this event task.
     *
     * @return formatted event string with type, status, description, and time range
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(printFormat),
                this.to.format(printFormat));
    }

    /**
     * Returns the storage representation of this event task.
     *
     * @return formatted event string suitable for saving to disk
     */
    public String saveString() {
        return String.format("E | %s | %s | %s", super.saveString(), this.from.format(saveFormat),
                this.to.format(saveFormat));
    }
}
