package nubish.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nubish.tasks.Task;
import nubish.tasks.Todo;

/**
 * Tests task list creation and task insertion behavior.
 */
public class TaskListTest {
    /**
     * Verifies that a newly created task list has no tasks.
     */
    @Test
    public void creating_newTaskList_createsAnEmptyList() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.size());
    }

    /**
     * Verifies that adding a task increases the list size and stores the task.
     */
    @Test
    public void add_newTaskList_addsTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("");
        taskList.add(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    /**
     * Verifies that duplicate task descriptions are rejected.
     */
    @Test
    public void add_duplicateDescription_throwsException() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));

        assertThrows(NubishException.class, () -> taskList.add(new Todo("read book")));
    }
}
