package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCalendar;


/**
 * Calendar Storage
 */
public interface CalendarStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getCalendarFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
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
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCalendar(ReadOnlyCalendar addressBook) throws IOException;

    /**
     * @see #saveCalendar(ReadOnlyCalendar)
     */
    void saveCalendar(ReadOnlyCalendar addressBook, Path filePath) throws IOException;

}
