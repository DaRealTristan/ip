package nubish.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests parser validation for malformed command input.
 */
public class ParserTest {
    private final TaskList taskList = new TaskList();
    private final UI ui = new UI();
    private final Parser parser = new Parser(null, ui, taskList);

    /**
     * Verifies that leading spaces are rejected with the command's format guidance.
     */
    @Test
    public void parse_leadingSpaceInDeadline_showsDeadlineFormat() {
        parser.parse(" deadline read book /by 12/10/2020 1800");

        assertTrue(ui.getLastResponse().contains("deadline {taskname} /by {deadline}"));
    }

    /**
     * Verifies that duplicated deadline parameters are rejected.
     */
    @Test
    public void parse_duplicateDeadlineParameter_showsDeadlineFormat() {
        parser.parse("deadline read book /by 12/10/2020 1800 /by 13/10/2020 1800");

        assertTrue(ui.getLastResponse().contains("deadline {taskname} /by {deadline}"));
    }

    /**
     * Verifies that invalid task indexes are reported as user-facing errors.
     */
    @Test
    public void parse_invalidMarkIndex_showsTaskNumberMessage() {
        parser.parse("mark abc");

        assertTrue(ui.getLastResponse().contains("Please put a valid task number"));
    }
}
