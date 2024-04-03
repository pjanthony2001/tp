package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());

    @Test
    public void equals() throws ParseException {
        FindCommandParser parser = new FindCommandParser();
        FindCommand findFirstCommand = parser.parse("find n/first");
        FindCommand findSecondCommand = parser.parse("find n/second");
        FindCommand findFirstCommandCopy = parser.parse("find n/first");

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // same values -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
    }

    @Test
    public void execute_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindCommandParser parser = new FindCommandParser();
        FindCommand command = parser.parse("find n/k");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
            Arrays.asList("k"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, new CommandResult(expectedMessage), expectedModel);
        assertEquals(Arrays.asList(model.getFilteredPersonList()), Arrays.asList(
            expectedModel.getFilteredPersonList()));
    }

    @Test
    public void execute_multipleParameters() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindCommandParser parser = new FindCommandParser();
        FindCommand command = parser.parse("find n/k p/953");
        List<Predicate<Person>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(
            Arrays.asList("k"));
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(
            Arrays.asList("953"));
        predicates.add(namePredicate);
        predicates.add(phonePredicate);
        Optional<Predicate<Person>> combinedPredicate = predicates.stream().reduce(Predicate::or);
        if (combinedPredicate.isPresent()) {
            expectedModel.updateFilteredPersonList(combinedPredicate.get());
        }
        assertCommandSuccess(command, model, new CommandResult(expectedMessage), expectedModel);
        assertEquals(Arrays.asList(model.getFilteredPersonList()), Arrays.asList(
            expectedModel.getFilteredPersonList()));
    }
}
