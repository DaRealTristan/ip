package nubish.utils;

import nubish.tasks.Deadline;
import nubish.tasks.Event;
import nubish.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filepath;
    private final TaskList taskList;

    public Storage(String filepath, TaskList taskList) {
        this.filepath = filepath;
        this.taskList = taskList;
    }

    public void load() throws FileNotFoundException {
        File f = new File(this.filepath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] line = s.nextLine().split("\\s*\\|\\s*");
            switch (line[0]) {
                case "E":
                    this.taskList.add(new Event(Integer.parseInt(line[1]) == 1, line[2], line[3], line[4]));
                    break;
                case "T":
                    this.taskList.add(new Todo(Integer.parseInt(line[1]) == 1, line[2]));
                    break;
                case "D":
                    this.taskList.add(new Deadline(Integer.parseInt(line[1]) == 1, line[2], line[3]));
                    break;
            }
        }
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (int i = 0; i < this.taskList.size(); i++) {
            fw.write(this.taskList.get(i).saveString() + "\n");
        }

        fw.close();
    }
}
