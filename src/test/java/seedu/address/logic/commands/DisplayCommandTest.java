package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DisplayCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

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
    public void execute_validKeywords_success() throws CommandException {
        // Use PersonBuilder to create a Person object with desired attributes
        Person alice = new PersonBuilder().withName("Alice").build();

        // Setup the model with the person data
        Model model = new ModelManager();
        model.addPerson(alice);

        // Create a DisplayCommand with valid keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice"));
        DisplayCommand displayCommand = new DisplayCommand(predicate);

        // Execute the DisplayCommand
        CommandResult commandResult = displayCommand.execute(model);

        // Verify that the CommandResult indicates success
        assertEquals(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1), commandResult.getFeedbackToUser());

        // Verify that the model has been updated as expected
        assertTrue(model.getFilteredPersonList().contains(alice));
    }

    @Test
    public void execute_personNotInList_failure() {
        // Create a DisplayCommand with a name that doesn't exist in the address book
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Julia"));
        DisplayCommand displayCommand = new DisplayCommand(predicate);

        try {
            // Execute the DisplayCommand
            displayCommand.execute(model);
        } catch (CommandException e) {
            // Verify that the thrown exception message matches the expected error message
            assertEquals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME, e.getMessage());
        }
    }
    @Test
    public void isDisplayCommand_true() {
        CommandResult commandResult = new CommandResult("Test feedback", false, false, true, null);
        assertTrue(commandResult.isDisplayCommand());
    }
    @Test
    public void getPerson_success() {
        Person person = new PersonBuilder().build();
        CommandResult commandResult = new CommandResult("Test feedback", person);
        assertEquals(person, commandResult.getPerson());
    }

    @Test
    public void getCommandString_returnsCommandString() {
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
