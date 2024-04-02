package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEDICAL_REVIEW_HOON;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;
import static seedu.address.testutil.TypicalEvents.UNEMPLOYMENT_TALK_IDA;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Calendar;
import seedu.address.model.ReadOnlyCalendar;

public class JsonCalendarStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCalendarStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCalendar_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCalendar(null));
    }

    private java.util.Optional<ReadOnlyCalendar> readCalendar(String filePath) throws DataLoadingException {
        return new JsonCalendarStorage(Paths.get(filePath)).readCalendar(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCalendar("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readCalendar("notJsonFormatCalendar.json"));
    }

    @Test
    public void readCalendar_invalidEventCalendar_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCalendar("invalidEventCalendar.json"));
    }

    @Test
    public void readCalendar_invalidAndValidEventCalendar_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCalendar("invalidAndValidEventCalendar.json"));
    }

    @Test
    public void readAndSaveCalendar_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCalendar.json");
        Calendar original = getTypicalCalendar();
        JsonCalendarStorage jsonCalendarStorage = new JsonCalendarStorage(filePath);

        // Save in new file and read back
        jsonCalendarStorage.saveCalendar(original, filePath);
        ReadOnlyCalendar readBack = jsonCalendarStorage.readCalendar(filePath).get();
        assertEquals(original, new Calendar(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEvent(MEDICAL_REVIEW_HOON);
        original.removeEvent(MEETING_WITH_ALICE);
        jsonCalendarStorage.saveCalendar(original, filePath);
        readBack = jsonCalendarStorage.readCalendar(filePath).get();
        assertEquals(original, new Calendar(readBack));

        // Save and read without specifying file path
        original.addEvent(UNEMPLOYMENT_TALK_IDA);
        jsonCalendarStorage.saveCalendar(original); // file path not specified
        readBack = jsonCalendarStorage.readCalendar().get(); // file path not specified
        assertEquals(original, new Calendar(readBack));

    }

    @Test
    public void saveCalendar_nullCalendar_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCalendar(null, "SomeFile.json"));
    }

    /**
     * Saves {@code calendar} at the specified {@code filePath}.
     */
    private void saveCalendar(ReadOnlyCalendar calendar, String filePath) {
        try {
            new JsonCalendarStorage(Paths.get(filePath))
                    .saveCalendar(calendar, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCalendar_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCalendar(new Calendar(), null));
    }
}
