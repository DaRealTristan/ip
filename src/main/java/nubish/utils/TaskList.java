package nubish.utils;

import nubish.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the current list of tasks.
 */
public class TaskList {
    private List<Task> tasklist = new ArrayList<>();

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasklist.add(task);
    }

    /**
     * Removes and returns the task at the given zero-based index.
     *
     * @param i zero-based index of the task to remove
     * @return removed task
     */
    public Task remove(int i) {
        return tasklist.remove(i);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param i zero-based index of the task to retrieve
     * @return task at the given index
     */
    public Task get(int i) {
        return tasklist.get(i);
    }
}
