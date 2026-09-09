package nubish.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import nubish.tasks.Deadline;
import nubish.tasks.Event;
import nubish.tasks.Todo;


/**
 * Handles loading tasks from disk and saving tasks back to disk.
 */
public class Storage {
    private final String filepath;
    private final TaskList taskList;

    /**
     * Creates storage backed by the given file path and task list.
     *
     * @param filepath path to the save file
     * @param taskList task list to load into and save from
     */
    public Storage(String filepath, TaskList taskList) {
        this.filepath = filepath;
        this.taskList = taskList;
    }

    /**
     * Loads saved tasks from the configured file into the task list.
     *
     * @throws FileNotFoundException if the save file does not exist
     */
    public void load() throws FileNotFoundException {
        File f = new File(this.filepath);
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String savedTask = scanner.nextLine();
                if (savedTask.isBlank()) {
                    continue;
                }

                String[] line = savedTask.split("\\s*\\|\\s*");
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
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Saves all tasks in the task list to the configured file.
     *
     * @throws IOException if the file cannot be written
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (int i = 0; i < this.taskList.size(); i++) {
            fw.write(this.taskList.get(i).saveString() + "\n");
        }

        fw.close();
    }
}
