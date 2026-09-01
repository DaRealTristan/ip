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
     * @param index zero-based index of the task to remove
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
     * @param index zero-based index of the task to retrieve
     * @return task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a task list containing tasks whose descriptions contain the keyword.
     *
     * @param keyword text to search for in task descriptions
     * @return task list containing matching tasks
     */
    public TaskList find(String keyword) {
        TaskList tasklist = new TaskList();

        for (Task t: this.tasks) {
            if (t.getDescription().contains(keyword)) {
                tasklist.add(t);
            }
        }

        return tasklist;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder("\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.get(i);
            list.append(String.format("%d. %s\n", i + 1, t.toString()));
        }

        return (list.toString());
    }
}
