package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCalendar;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, CalendarStorage {
    // ================ UserPref methods ==============================
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    // ================ AddressBook methods ==============================
    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;


    // ================ Calendar methods ==============================
    @Override
    Path getCalendarFilePath();

    @Override
    Optional<ReadOnlyCalendar> readCalendar() throws DataLoadingException;

    @Override
    Optional<ReadOnlyCalendar> readCalendar(Path filePath) throws DataLoadingException;

    @Override
    void saveCalendar(ReadOnlyCalendar calendar) throws IOException;

    @Override
    void saveCalendar(ReadOnlyCalendar calendar, Path filePath) throws IOException;
}
