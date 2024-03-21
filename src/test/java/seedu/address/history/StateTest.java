package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.CommandUtil.getCommandStub;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.Command;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

import java.util.function.Predicate;


class StateTest {
    private State state;
    @BeforeEach
    void setup() {
        AddressBook addressBook = getTypicalAddressBook();
        Command command = getCommandStub();
        FilteredList<Person> filteredPersons = new FilteredList<>(addressBook.getPersonList());
        state = new State(command, addressBook, filteredPersons, person -> true);
    }
    @Test
    void getAddressBook() {
        AddressBook original = getTypicalAddressBook();
        ReadOnlyAddressBook retrieved = state.getAddressBook();
        assertEquals(original, retrieved);
    }

    @Test
    void getCommandSuccess() {
        Command original = getCommandStub();
        Command retrieved = state.getCommand();
        assertEquals(original, retrieved);
    }

    @Test
    void getCommandFailure() {
        Command original = getCommandStub();
        Command retrieved = state.getCommand();
        assertEquals(original, retrieved);
    }

    @Test
    void getPredicateSuccess() {
        Predicate<Person> expectedPredicate = person -> true;
        Command retrieved = state.getCommand();
        assertEquals(expectedPredicate, retrieved);
    }

    @Test
    void getPredicateFailure() {
        Predicate<Person> expectedPredicate = person -> false;
        Command retrieved = state.getCommand();
        assertNotEquals(expectedPredicate, retrieved);
    }
}
