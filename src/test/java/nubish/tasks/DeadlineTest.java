package nubish.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void getStatusIcon_newDeadline_returnsUndoneIcon() {
        Task task = new Deadline("read book", "12/10/2020 1800");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_savedDoneDeadline_returnsDoneIcon() {
        Task task = new Deadline(true ,"read book", "12/10/2020 1800");

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_savedUndoneDeadline_returnsUndoneIcon() {
        Task task = new Deadline(false, "read book", "12/10/2020 1800");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_markedDeadline_returnsDoneIcon() {
        Task task = new Deadline("read book", "12/10/2020 1800");

        task.markAsDone();

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_unmarkedTask_returnsUndoneIcon() {
        Task task = new Deadline(true, "read book", "12/10/2020 1800");

        task.unmarkAsDone();

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void saveString_unmarkedDeadline_returnsCorrectFormat() {
        Task task = new Deadline("read book", "12/10/2020 1800");

        assertEquals("D | 0 | read book | 12/10/2020 1800", task.saveString());
    }

    @Test
    public void convertToDateTime_throwsErrorWhenWrongFormatIsUsed() {
        assertThrows(DateTimeParseException.class, () -> new Deadline("read book", "12/10/20 1800"));
    }
}
