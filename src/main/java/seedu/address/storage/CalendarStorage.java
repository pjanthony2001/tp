package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCalendar;
import seedu.address.model.ReadOnlyCalendar;


/**
 * Represents a storage for the Calendar
 */
public interface CalendarStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getCalendarFilePath();

    /**
     * Returns Calander data as a {@link ReadOnlyCalendar}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCalendar> readCalendar() throws DataLoadingException;

    /**
     * @see #getCalendarFilePath()
     */
    Optional<ReadOnlyCalendar> readCalendar(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCalendar} to the storage.
     * @param calendar cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCalendar(ReadOnlyCalendar calendar) throws IOException;

    /**
     * @see #saveCalendar(ReadOnlyCalendar)
     */
    void saveCalendar(ReadOnlyCalendar calendar, Path filePath) throws IOException;

}
