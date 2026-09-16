package nubish.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests storage behavior for missing and malformed save files.
 */
public class StorageTest {
    @TempDir
    private Path temporaryDirectory;

    /**
     * Verifies that a missing save file is created during loading.
     */
    @Test
    public void load_missingFile_createsFile() throws IOException {
        Path saveFile = temporaryDirectory.resolve("data").resolve("nubish.txt");
        Storage storage = new Storage(saveFile.toString(), new TaskList());

        storage.load();

        assertTrue(Files.exists(saveFile));
    }

    /**
     * Verifies that malformed saved content is rejected with line information.
     */
    @Test
    public void load_malformedLine_throwsExceptionWithLineNumber() throws IOException {
        Path saveFile = temporaryDirectory.resolve("nubish.txt");
        Files.writeString(saveFile, "T | 2 | read book\n");
        Storage storage = new Storage(saveFile.toString(), new TaskList());

        NubishException exception = assertThrows(NubishException.class, storage::load);

        assertEquals("Invalid save file at line 1: invalid task status", exception.getMessage());
    }
}
