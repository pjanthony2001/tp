package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DisplayCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;

public class DisplayCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());

    @Test
    public void execute_empty_command() {
        DisplayCommand displaycommand;
        DisplayCommandParser parser = new DisplayCommandParser();

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        try {
            displaycommand = parser.parse("");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        assertCommandFailure(displaycommand, model, expectedMessage);
    }


    @Test
    public void getCommandStringTest() {
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        DisplayCommand displayCommand = new DisplayCommand(predicate);
        String expected = DisplayCommand.COMMAND_WORD;
        assertEquals(expected, displayCommand.getCommandString());
    }

    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void equals() {
        DisplayCommandParser parser = new DisplayCommandParser();
        DisplayCommand first;
        DisplayCommand second;
        DisplayCommand firstCopy;
        try {
            first = parser.parse("Alice");
            second = parser.parse("Bob");
            firstCopy = parser.parse("Alice");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        // same object -> returns true
        assertTrue(first.equals(first));

        // same values -> returns true
        assertTrue(first.equals(firstCopy));

        // different types -> returns false
        assertFalse(first.equals(1));

        // null -> returns false
        assertFalse(first.equals(null));

        // different person -> returns false
        assertFalse(first.equals(second));
    }
}
