package seedu.address.history;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.Command;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;



/**
 * The `State` class represents a snapshot of the ConnectCare application's state at a specific point in time.
 * It contains information about the executed command and the list of tasks at that time.
 */
public class State {
    private final Command command;
    private final ReadOnlyAddressBook addressBook;
    private final Predicate<? super Person> filteredPersonsListPredicate;

    /**
     * Constructs a new State object with the given command and task list.
     *
     * @param command                      The command executed to reach this state.
     * @param addressBook                  The list of tasks at this state.
     * @param filteredPersonsListPredicate The predicate of the filtered list
     */
    public State(Command command, ReadOnlyAddressBook addressBook,
                 Predicate<? super Person> filteredPersonsListPredicate) {
        this.command = command;
        this.addressBook = addressBook;
        this.filteredPersonsListPredicate = filteredPersonsListPredicate;
    }

    /**
     * Gets the list of tasks at this state.
     *
     * @return The list of tasks.
     */
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    /**
     * Gets the command executed to reach this state.
     *
     * @return The command.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Gets the command executed to reach this state.
     *
     * @return The command.
     */
    public Predicate<? super Person> getFilteredPersonsListPredicate() {
        return filteredPersonsListPredicate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof State)) {
            return false;
        }

        State otherState = (State) other;

        FilteredList<Person> filteredListOther = new FilteredList<>(otherState.addressBook.getPersonList());
        filteredListOther.setPredicate(otherState.filteredPersonsListPredicate);

        FilteredList<Person> filteredList = new FilteredList<>(otherState.addressBook.getPersonList());
        filteredList.setPredicate(filteredPersonsListPredicate);

        return addressBook.equals(otherState.addressBook)
                && command.equals(otherState.command)
                && filteredList.equals(filteredListOther);

    }
}
