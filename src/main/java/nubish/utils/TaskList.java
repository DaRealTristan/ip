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

    public TaskList find(String keyword) {
        TaskList tasklist = new TaskList();

        for (Task t: this.tasklist) {
            if (t.getDescription().contains(keyword)) {
                tasklist.add(t);
            }
        }

        return tasklist;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder("\n");
        for (int i = 0; i < this.tasklist.size(); i++) {
            Task t = this.get(i);
            list.append(String.format("%d. %s\n", i + 1, t.toString()));
        }

        return (list.toString());
    }
}
