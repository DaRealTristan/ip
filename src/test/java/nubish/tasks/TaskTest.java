package nubish.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the status icon returned by {@link Task}.
 */
public class TaskTest {
    @Test
    public void getStatusIcon_newTask_returnsUndoneIcon() {
        Task task = new Task("read book");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_savedDoneTask_returnsDoneIcon() {
        Task task = new Task(true, "read book");

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_savedUndoneTask_returnsUndoneIcon() {
        Task task = new Task(false, "read book");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_markedTask_returnsDoneIcon() {
        Task task = new Task("read book");

        task.markAsDone();

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_unmarkedTask_returnsUndoneIcon() {
        Task task = new Task(true, "read book");

        task.unmarkAsDone();

        assertEquals(" ", task.getStatusIcon());
    }
}
