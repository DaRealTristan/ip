package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("hhmm MMM d yyyy");

    public Event(String description, String from, String to) {
        super(description);
        this.from = convertToDateTime(from);
        this.to = convertToDateTime(from);
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = convertToDateTime(from);
        this.to = convertToDateTime(from);
    }

    public LocalDateTime convertToDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, saveFormat);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(printFormat),
                this.to.format(printFormat));
    }

    public String saveString() {
        return String.format("E | %s | %s | %s", super.saveString(), this.from.format(saveFormat),
                this.to.format(saveFormat));
    }
}