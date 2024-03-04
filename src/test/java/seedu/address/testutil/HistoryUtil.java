package seedu.address.testutil;

import static seedu.address.testutil.CommandUtil.getCommandStub;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import seedu.address.history.State;
import seedu.address.model.AddressBook;

/**
 * History Utility that can be used for testing
 */
public class HistoryUtil {
    private static final AddressBook START_ADDRESSBOOK = getTypicalAddressBook();
    public static final State TYPICAL_START_STATE = new State(getCommandStub(), START_ADDRESSBOOK);
}
