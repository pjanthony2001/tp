package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Calendar;
import seedu.address.testutil.TypicalEvents;

public class JsonSerializableCalendarTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCalendarTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsCalendar.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventCalendar.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventCalendar.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableCalendar dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableCalendar.class).get();
        Calendar calendarFromFile = dataFromFile.toModelType();
        Calendar typicalEventsCalendar = TypicalEvents.getTypicalCalendar();
        assertEquals(calendarFromFile, typicalEventsCalendar);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCalendar dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableCalendar.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableCalendar dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableCalendar.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCalendar.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }

}
