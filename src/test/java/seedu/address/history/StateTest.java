package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.CommandUtil.getCommandStub;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.model.AddressBook;



class StateTest {
    private State state;
    @BeforeEach
    void setup() {
        AddressBook addressBook = getTypicalAddressBook();
        Command command = getCommandStub();
        state = new State(command, addressBook);
    }
    @Test
    void getAddressBook() {
        AddressBook original = getTypicalAddressBook();
        AddressBook retrieved = state.getAddressBook();
        assertEquals(original, retrieved);
    }

    @Test
    void getCommand() {
        Command original = getCommandStub();
        Command retrieved = state.getCommand();
        assertEquals(original, retrieved);
    }
}
