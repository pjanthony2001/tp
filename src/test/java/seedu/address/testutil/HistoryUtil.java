package seedu.address.testutil;

import static seedu.address.logic.commands.StartCommand.getStartCommand;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
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
            PREDICATE_SHOW_ALL_PERSONS);

    public static final State TYPICAL_SECOND_STATE = new State(getCommandStub(),
            START_ADDRESSBOOK,
            PREDICATE_SHOW_ALL_PERSONS);
}
