package nubish.utils;

import nubish.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasklist = new ArrayList<>();

    public void add(Task task) {
        tasklist.add(task);
    }

    public Task remove(int i) {
        return tasklist.remove(i);
    }

    public int size() {
        return tasklist.size();
    }

    public Task get(int i) {
        return tasklist.get(i);
    }
}
