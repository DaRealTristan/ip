package utils;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasklist = new ArrayList<>();

    public static void add(Task task) {
        tasklist.add(task);
    }

    public static Task remove(int i) {
        return tasklist.remove(i);
    }

    public static int size() {
        return tasklist.size();
    }

    public static Task get(int i) {
        return tasklist.get(i);
    }
}
