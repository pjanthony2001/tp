package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindCommandParser;
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

    @Test
    public void execute_zeroKeywords_fail() {
        FindCommand command;
        FindCommandParser parser = new FindCommandParser();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        try {
            command = parser.parse("");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommand command;
        FindCommandParser parser = new FindCommandParser();
        try {
            command = parser.parse("n/k");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
            Arrays.asList("K"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(model.getFilteredPersonList()), Arrays.asList(
            expectedModel.getFilteredPersonList()));
    }

    @Test
    public void execute_multipleParameters() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command;
        FindCommandParser parser = new FindCommandParser();
        try {
            command = parser.parse("n/k p/953");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        List<Predicate<Person>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(
            Arrays.asList("k"));
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(
            Arrays.asList("953"));
        predicates.add(namePredicate);
        predicates.add(phonePredicate);
        Optional<Predicate<Person>> combinedPredicate = predicates.stream().reduce(Predicate::and);
        if (combinedPredicate.isPresent()) {
            expectedModel.updateFilteredPersonList(combinedPredicate.get());
        }
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(model.getFilteredPersonList()), Arrays.asList(
            expectedModel.getFilteredPersonList()));
    }
}
