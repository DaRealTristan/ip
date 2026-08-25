import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import utils.*;


public class Nubish {
    private final Storage storage;
    private final TaskList taskList;
    private final UI ui;
    private final Parser parser;

    public Nubish(String filepath) {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.storage = new Storage(filepath, this.taskList);
        this.parser = new Parser(this.storage, this.ui, this.taskList);
    }

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

    public static void main(String[] args) {
        final String FILEPATH = "./nubish.txt";
        new Nubish(FILEPATH).run();
    }
}