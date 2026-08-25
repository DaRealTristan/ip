package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime deadline;
    private DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("hhmm MMM d yyyy");

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = convertToDateTime(deadline);
    }

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = convertToDateTime(deadline);
    }

    public LocalDateTime convertToDateTime(String dateTimeString) {
        System.out.println(dateTimeString);
        return LocalDateTime.parse(dateTimeString, saveFormat);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(printFormat));
    }

    @Override
    public String saveString() {
        return String.format("D | %s | %s", super.saveString(), this.deadline.format(saveFormat));
    }
}