package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.StartCommand.getStartCommand;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.CommandUtil.getCommandStub;
import static seedu.address.testutil.HistoryUtil.TYPICAL_SECOND_MODEL_STATE;
import static seedu.address.testutil.HistoryUtil.TYPICAL_START_MODEL_STATE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.Command;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;



class ModelStateTest {
    private ModelState modelState;
    private AddressBook addressBook;
    private FilteredList<Person> filteredPersons;
    private Command command;
    @BeforeEach
    void setup() {
        addressBook = getTypicalAddressBook();
        command = getCommandStub();
        filteredPersons = new FilteredList<>(addressBook.getPersonList());
        modelState = new ModelState(command, addressBook, PREDICATE_SHOW_ALL_PERSONS);
    }
    @Test
    void getAddressBook_typicalAddressBook_successfullyReturnsTypicalAddressBook() {
        AddressBook original = getTypicalAddressBook();
        ReadOnlyAddressBook retrieved = modelState.getAddressBook();
        assertEquals(original, retrieved);
    }

    @Test
    void getCommand_commandStubTracked_successfullyReturnsCommandStub() {
        Command original = getCommandStub();
        Command retrieved = modelState.getCommand();
        assertEquals(original, retrieved);
    }

    @Test
    void getPredicate_showAllPersons_successfullyReturnsShowAllPersons() {
        int sizeBeforePredicate = filteredPersons.size();
        Predicate<? super Person> retrieved = modelState.getFilteredPersonsListPredicate();
        filteredPersons.setPredicate(retrieved);
        int sizeAfterPredicate = filteredPersons.size();
        assertEquals(sizeBeforePredicate, sizeAfterPredicate);
    }

    @Test
    void equalsTest() {
        assertNotEquals(TYPICAL_START_MODEL_STATE, TYPICAL_SECOND_MODEL_STATE);
        assertEquals(TYPICAL_START_MODEL_STATE, TYPICAL_START_MODEL_STATE); //check for same pointer
        assertNotEquals(TYPICAL_SECOND_MODEL_STATE, null); //check for null
        assertEquals(TYPICAL_START_MODEL_STATE, new ModelState(getStartCommand(),
                getTypicalAddressBook(),
                PREDICATE_SHOW_ALL_PERSONS));
    }
}
