package nubish.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nubish.utils.NubishException;

/**
 * Tests event date range validation.
 */
public class EventTest {
    /**
     * Verifies that an event cannot end at the same time it starts.
     */
    @Test
    public void constructor_sameStartAndEnd_throwsException() {
        assertThrows(NubishException.class, () -> new Event("meeting", "12/10/2020 1800", "12/10/2020 1800"));
    }

    /**
     * Verifies that an event cannot end before it starts.
     */
    @Test
    public void constructor_startAfterEnd_throwsException() {
        assertThrows(NubishException.class, () -> new Event("meeting", "12/10/2020 1800", "12/10/2020 1700"));
    }
}
