import java.util.Scanner;

public class Nubish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = """
              _   _ _   _ ____ ___ ____  _   _ 
             | \\ | | | | | __ )_ _/ ___|| | | |
@@ -8,5 +12,26 @@ public static void main(String[] args) {
             |_| \\_|\\___/|____/___|____/|_| |_|
            """;
        System.out.println(logo + "Hello! I'm Nubish.\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                String response = """
                        _________________________________________________
                        Nubish: Bye. Hope to see you again soon!
                        _________________________________________________
                        """;
                System.out.println(response);
                break;
            } else {
                String response = """
                        _________________________________________________
                        Nubish: %s
                        _________________________________________________
                        """;
                System.out.printf(response, input);
            }
        }
    }
}