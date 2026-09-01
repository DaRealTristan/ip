package nubish.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

/**
 * Tests deadline task status, saving, and date parsing behavior.
 */
public class DeadlineTest {
    /**
     * Verifies that a new deadline task starts as not done.
     */
    @Test
    public void getStatusIcon_newDeadline_returnsUndoneIcon() {
        Task task = new Deadline("read book", "12/10/2020 1800");

        assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Verifies that a loaded done deadline task shows the done icon.
     */
    @Test
    public void getStatusIcon_savedDoneDeadline_returnsDoneIcon() {
        Task task = new Deadline(true, "read book", "12/10/2020 1800");

        assertEquals("X", task.getStatusIcon());
    }

    /**
     * Verifies that a loaded undone deadline task shows the undone icon.
     */
    @Test
    public void getStatusIcon_savedUndoneDeadline_returnsUndoneIcon() {
        Task task = new Deadline(false, "read book", "12/10/2020 1800");

        assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Verifies that marking a deadline task changes its status icon to done.
     */
    @Test
    public void getStatusIcon_markedDeadline_returnsDoneIcon() {
        Task task = new Deadline("read book", "12/10/2020 1800");

        task.markAsDone();

        assertEquals("X", task.getStatusIcon());
    }

    /**
     * Verifies that unmarking a deadline task changes its status icon to undone.
     */
    @Test
    public void getStatusIcon_unmarkedTask_returnsUndoneIcon() {
        Task task = new Deadline(true, "read book", "12/10/2020 1800");

        task.unmarkAsDone();

        assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Verifies that an undone deadline task is saved in the expected file format.
     */
    @Test
    public void saveString_unmarkedDeadline_returnsCorrectFormat() {
        Task task = new Deadline("read book", "12/10/2020 1800");

        assertEquals("D | 0 | read book | 12/10/2020 1800", task.saveString());
    }

    /**
     * Verifies that deadlines must use the expected date-time format.
     */
    @Test
    public void convertToDateTime_throwsErrorWhenWrongFormatIsUsed() {
        assertThrows(DateTimeParseException.class, () -> new Deadline("read book", "12/10/20 1800"));
    }
}
