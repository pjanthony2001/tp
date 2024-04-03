package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.StartCommand.getStartCommand;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.history.CommandState;
import seedu.address.history.History;
import seedu.address.history.HistoryManager;
import seedu.address.history.ModelState;
import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.commands.Command;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final Calendar calendar;
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private ObservableList<Person> source;
    private final History<ModelState> modelHistory;
    private final History<CommandState> commandHistory;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyCalendar calendar) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs
            + "and calendar " + calendar);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.calendar = new Calendar(calendar);
        this.source = this.addressBook.getPersonList();
        this.filteredPersons = new FilteredList<>(source);

        ModelState startModelState;
        CommandState startCommandState;
        try {
            startModelState = generateModelState(getStartCommand());
        } catch (HistoryException e) {
            ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
            startModelState = new ModelState(getStartCommand(),
                    sampleAddressBook,
                    PREDICATE_SHOW_ALL_PERSONS);
        }
        startCommandState = generateCommandState(getStartCommand().getCommandString());
        this.modelHistory = new HistoryManager<>(startModelState, false);
        this.commandHistory = new HistoryManager<>(startCommandState, true);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Calendar());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        this.source = this.addressBook.getPersonList();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person updatedPerson) {
        requireAllNonNull(target, updatedPerson);

        addressBook.setPerson(target, updatedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    public Predicate<? super Person> getFilteredPersonsListPredicate() {
        if (filteredPersons.getPredicate() == null) {
            return PREDICATE_SHOW_ALL_PERSONS;
        }
        return filteredPersons.getPredicate();
    }

    //============== Model History ===============================================================================

    /**
     * Gets current model state
     */
    @Override
    public ModelState getCurrentModelState() {
        return modelHistory.getCurrState();
    }

    /**
     * @param command Last command executed to reach this state
     */
    @Override
    public void updateModelState(Command command) throws HistoryException {
        if (command.isReversible()) {
            ModelState modelState = generateModelState(command);
            modelHistory.addState(modelState);
        }
    }

    /**
     * @param command Last command executed to reach this state
     * @return Generated state
     * @throws HistoryException if error while making deep copy
     */
    public ModelState generateModelState(Command command) throws HistoryException {
        try {
            return new ModelState(command,
                    getAddressBook().deepCopy(),
                    getFilteredPersonsListPredicate());
        } catch (IllegalValueException e) {
            throw new HistoryException("Error while generating state", e);
        }
    }

    /**
     * @param modelState ModelState to be restored
     */
    @Override
    public void restoreModelState(ModelState modelState) {
        ReadOnlyAddressBook newAddressBook = modelState.getAddressBook();
        setAddressBook(newAddressBook);

        Predicate<? super Person> newPredicate = modelState.getFilteredPersonsListPredicate();
        updateFilteredPersonList(newPredicate);
    }

    /**
     * @throws HistoryException If state cannot be rolled back
     */
    @Override
    public void rollBackState() throws HistoryException {
        modelHistory.rollBackState();
    }

    /**
     * @throws HistoryException If state cannot be rolled forward
     */
    @Override
    public void rollForwardState() throws HistoryException {
        modelHistory.rollForwardState();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return calendar.getEventList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    //=========== Calendar Accessors =============================================================
    @Override
    public ReadOnlyCalendar getCalendar() {
        return calendar;
    }
    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return calendar.hasEvent(event);
    }
    @Override
    public void addEvent(Event event) {
        calendar.addEvent(event);
    }
    @Override
    public void deleteEvent(Event key) {
        calendar.removeEvent(key);
    }
    @Override
    public void setEvents(List<Event> events) {
        calendar.setEvents(events);
    }

    //============== Command History ===============================================================================

    @Override
    public CommandState getCurrentCommandState() {
        return commandHistory.getCurrState();
    }

    @Override
    public void updateCommandState(String commandText) {
        CommandState commandState = generateCommandState(commandText);
        commandHistory.addState(commandState);
    }

    public CommandState generateCommandState(String commandText) {
        return new CommandState(commandText);
    }

    @Override
    public String retrievePreviousCommand() throws HistoryException {
        commandHistory.rollBackState();
        CommandState previousCommand = commandHistory.getCurrStateHasBuffer();
        String previousCommandText = previousCommand.getCommandText();
        return previousCommandText;
    }

    @Override
    public String retrieveNextCommand() throws HistoryException {
        commandHistory.rollForwardState();
        CommandState nextCommand = commandHistory.getCurrStateHasBuffer();
        String nextCommandText = nextCommand.getCommandText();
        return nextCommandText;
    }
}
