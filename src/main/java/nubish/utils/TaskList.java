package nubish.utils;

import java.util.ArrayList;
import java.util.List;


import nubish.tasks.Task;

/**
 * Stores and provides access to the current tasks.
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given zero-based index.
     *
     * @param i zero-based index of the task to remove
     * @return removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param i zero-based index of the task to retrieve
     * @return task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
