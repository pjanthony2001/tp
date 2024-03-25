package seedu.address.testutil;

import static seedu.address.logic.commands.StartCommand.getStartCommand;
import static seedu.address.testutil.CommandUtil.getCommandStub;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import seedu.address.history.State;
import seedu.address.model.AddressBook;

/**
 * History Utility that can be used for testing
 */
public class HistoryUtil {
    private static final AddressBook START_ADDRESSBOOK = getTypicalAddressBook();
    public static final State TYPICAL_START_STATE = new State(getStartCommand(),
            START_ADDRESSBOOK,
            START_ADDRESSBOOK.getPersonList(),
            person -> true);

    public static final State TYPICAL_SECOND_STATE = new State(getCommandStub(),
            START_ADDRESSBOOK,
            START_ADDRESSBOOK.getPersonList(),
            person -> false);
}
