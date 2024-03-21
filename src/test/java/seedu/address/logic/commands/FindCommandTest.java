package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindCommandParser parser = new FindCommandParser();
        FindCommand findFirstCommand;
        FindCommand findSecondCommand;
        FindCommand findFirstCommandCopy;
        try {
            findFirstCommand = parser.parse("n/first");
            findSecondCommand = parser.parse("n/second");
            findFirstCommandCopy = parser.parse("n/first");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // same values -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
    }

}
