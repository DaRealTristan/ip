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
     * Verifies that malformed event date-times show event syntax and date-order guidance.
     */
    @Test
    public void parse_invalidEventDateTime_showsEventDateTimeFormat() {
        parser.parse("event meeting /from 30/02/2020 1800 /to 31/02/2020 1800");

        assertTrue(ui.getLastResponse().contains("event {eventName} /from {startDate} /to {enddate}"));
        assertTrue(ui.getLastResponse().contains("Start times must be earlier than end times."));
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
