package seedu.address.testutil;

import static seedu.address.logic.commands.StartCommand.getStartCommand;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.CommandUtil.getCommandStub;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import seedu.address.history.ModelState;
import seedu.address.model.AddressBook;

/**
 * History Utility that can be used for testing
 */
public class HistoryUtil {
    private static final AddressBook START_ADDRESSBOOK = getTypicalAddressBook();
    public static final ModelState TYPICAL_START_MODEL_STATE = new ModelState(getStartCommand(),
            START_ADDRESSBOOK,
            PREDICATE_SHOW_ALL_PERSONS);

    public static final ModelState TYPICAL_SECOND_MODEL_STATE = new ModelState(getCommandStub(),
            START_ADDRESSBOOK,
            PREDICATE_SHOW_ALL_PERSONS);
}
