package nubish.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void getStatusIcon_newTodo_returnsUndoneIcon() {
        Task task = new Todo("read book");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_savedDoneTodo_returnsDoneIcon() {
        Task task = new Todo(true, "read book");

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_savedUndoneTodo_returnsUndoneIcon() {
        Task task = new Todo(false, "read book");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_markedTodo_returnsDoneIcon() {
        Task task = new Todo("read book");

        task.markAsDone();

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_unmarkedTodo_returnsUndoneIcon() {
        Task task = new Todo(true, "read book");

        task.unmarkAsDone();

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void saveString_unmarkedTodo_returnsCorrectFormat() {
        Task task = new Todo("read book");

        assertEquals("T | 0 | read book", task.saveString());
    }
}
