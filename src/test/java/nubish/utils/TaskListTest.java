package nubish.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nubish.tasks.Task;
import nubish.tasks.Todo;
import org.junit.jupiter.api.Test;

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
    public void adding_to_newTaskList_works() {
        TaskList taskList = new TaskList();
        Task task = new Todo("");
        taskList.add(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }
}
