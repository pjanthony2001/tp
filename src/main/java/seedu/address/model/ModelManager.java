package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.history.History;
import seedu.address.history.HistoryManager;
import seedu.address.history.State;
import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.commands.Command;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAdaptedPerson;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private final History history;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) throws HistoryException {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        history = new HistoryManager(generateState(null));
    }

    public ModelManager() throws HistoryException {
        this(new AddressBook(), new UserPrefs());
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
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
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
    public void setFilteredPersonsList(ObservableList<Person> filteredPersonsList,
                                       Predicate<? super Person> predicate) {
        this.filteredPersons = new FilteredList<>(filteredPersonsList);
        updateFilteredPersonList(predicate);
    }

    /**
     * @return Deep Copy of the current FilteredPersonsList
     * @throws IllegalValueException
     */
    public ObservableList<Person> deepCopyFilteredPersonsList() throws IllegalValueException {
        List<JsonAdaptedPerson> jsonList = filteredPersons.getSource()
                .stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList());
        List<Person> personList = new ArrayList<>();
        for (JsonAdaptedPerson jsonPerson : jsonList) {
            personList.add(jsonPerson.toModelType());
        }
        return FXCollections.observableList(personList);
    }
    public Predicate<? super Person> getFilteredPersonsListPredicate() {
        if (filteredPersons.getPredicate() == null) {
            return PREDICATE_SHOW_ALL_PERSONS;
        }
        return filteredPersons.getPredicate();
    }
    //============== History ===============================================================================
    /**
     * Gets current state
     */
    @Override
    public State getCurrentState() {
        return history.getCurrState();
    }

    /**
     * @param command Last command executed to reach this state
     */
    @Override
    public void updateState(Command command) throws HistoryException {
        if (command.isTracked()) {
            State state = generateState(command);
            history.addState(state);
        }
    }

    /**
     * @param command Last command executed to reach this state
     * @return Generated state
     * @throws HistoryException if error while making deep copy
     */
    public State generateState(Command command) throws HistoryException {
        try {
            return new State(command,
                    getAddressBook().deepCopy(),
                    deepCopyFilteredPersonsList(),
                    getFilteredPersonsListPredicate());
        } catch (IllegalValueException e) {
            throw new HistoryException("Error while creating deepcopy", e);
        }
    }
    /**
     * @param state State to be restored
     */
    @Override
    public void restoreState(State state) {
        ObservableList<Person> newFilteredPersons = state.getFilteredList();
        ReadOnlyAddressBook newAddressBook = state.getAddressBook();
        Predicate<? super Person> newPredicate = state.getFilteredPersonsListPredicate();

        setAddressBook(newAddressBook);
        setFilteredPersonsList(newFilteredPersons, newPredicate);
        System.out.println(filteredPersons.getSource());
        System.out.println(filteredPersons);
    }
    /**
     * @throws HistoryException If state cannot be rolled back
     */
    @Override
    public void rollBackState() throws HistoryException {
        history.rollBackState();
    }

    /**
     * @throws HistoryException If state cannot be rolled forward
     */
    @Override
    public void rollForwardState() throws HistoryException {
        history.rollForwardState();
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

}
