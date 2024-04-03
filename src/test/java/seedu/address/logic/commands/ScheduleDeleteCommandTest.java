package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEADING_MEETING_WITH_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.Heading;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ScheduleDeleteCommand.
 */
public class ScheduleDeleteCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());

    @Test
    public void execute_validHeadingUnfilteredList_success() {
        Event eventToDelete = MEETING_WITH_ALICE;
        ScheduleDeleteCommand deleteCommand = new ScheduleDeleteCommand(eventToDelete.getHeading());

        String expectedMessage = String.format(ScheduleDeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(eventToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getCalendar());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unavailableHeadingUnfilteredList_throwsCommandException() {
        Heading heading = new Heading("Invalid Heading");
        ScheduleDeleteCommand deleteCommand = new ScheduleDeleteCommand(heading);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_HEADING);
    }

    @Test
    public void equals() {
        Heading headingFirst = new Heading("First");
        Heading headingSecond = new Heading("Second");
        ScheduleDeleteCommand deleteFirstCommand = new ScheduleDeleteCommand(headingFirst);
        ScheduleDeleteCommand deleteSecondCommand = new ScheduleDeleteCommand(headingSecond);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ScheduleDeleteCommand deleteFirstCommandCopy = new ScheduleDeleteCommand(headingFirst);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void getCommandStringTest() {
        ScheduleDeleteCommand deleteCommand = new ScheduleDeleteCommand(new Heading(VALID_HEADING_MEETING_WITH_ALICE));
        String expected = ScheduleDeleteCommand.COMMAND_WORD;
        assertEquals(expected, deleteCommand.getCommandString());
    }

}
