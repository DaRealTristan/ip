package nubish;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import nubish.utils.Parser;
import nubish.utils.Storage;
import nubish.utils.TaskList;
import nubish.utils.UI;

/**
 * Runs the Nubish command-line task manager.
 */
public class Nubish {
    private final Storage storage;
    private final TaskList taskList;
    private final UI ui;
    private final Parser parser;

    /**
     * Creates a Nubish instance that stores tasks at the specified file path.
     */
    public Nubish(String filepath) {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.storage = new Storage(filepath, this.taskList);
        this.parser = new Parser(this.storage, this.ui, this.taskList);
    }

    /**
     * Starts the main command loop and saves tasks before exiting.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            storage.load();
        } catch (FileNotFoundException e) {
            System.out.println("No saved file to load from");
        }

        ui.greet();
        String input = scanner.nextLine().trim();

        while (parser.parse(input)) {
            input = scanner.nextLine().trim();
        }

        try {
            storage.save();
        } catch (IOException e) {
            System.out.printf("OOPs seems like there was an error saving your data: %s", e.getMessage());
        }
    }

    /**
     * Starts Nubish with the default save file.
     */
    public static void main(String[] args) {
        final String filepath = "./nubish.txt";
        new Nubish(filepath).run();
    }
}
