package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public void load(String filepath, TaskList taskList) throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] line = s.nextLine().split("\\s*\\|\\s*");
            switch (line[0]) {
                case "E":
                    taskList.add(new Event(Integer.parseInt(line[1]) == 1, line[2], line[3], line[4]));
                    break;
                case "T":
                    taskList.add(new Todo(Integer.parseInt(line[1]) == 1, line[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(Integer.parseInt(line[1]) == 1, line[2], line[3]));
                    break;
            }
        }
    }

    public void save(String filepath, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).saveString() + "\n");
        }

        fw.close();
    }
}
