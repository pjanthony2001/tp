package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



public class UndoCommandIntegrationTest {

    private Model model;
    @BeforeEach
    void setUp() {
        model = new ModelManager();
    }

    @Test
    void execute_startStateRollback_failure() {
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_NO_ROLLBACK);
    }

    @Test
    void execute_undoAddCommand_success() {
        try {
            Person validPerson = new PersonBuilder().build();
            AddCommand addCommand = new AddCommand(validPerson);
            addCommand.execute(model);
            assertEquals(Arrays.asList(validPerson), model.getFilteredPersonList());
            // Assert that add command succeeded

            model.updateModelState(addCommand); // Update state

            CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                    addCommand.getCommandString()));
            Model expectedModel = new ModelManager();

            assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
        } catch (CommandException e) {
            fail("Failed to execute add", e);
        } catch (HistoryException e) {
            fail("Failed to update model state", e);
        }
    }

    @Test
    void execute_undoClearCommand_commandSuccess() {
        try {
            model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());
            ClearCommand clearCommand = new ClearCommand();
            clearCommand.execute(model);
            assertEquals(new ArrayList<Person>(), model.getFilteredPersonList());
            // Assert that clear command succeeded

            model.updateModelState(clearCommand); // Update state

            CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                    clearCommand.getCommandString()));
            Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());

            assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
        } catch (HistoryException e) {
            fail("Failed to update model state", e);
        }
    }

    @Test
    void execute_undoHelpCommand_commandFailure() {
        try {
            model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCalendar());
            HelpCommand helpCommand = new HelpCommand();
            CommandResult commandResult = helpCommand.execute(model);
            CommandResult expectedHelpCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
            assertEquals(expectedHelpCommandResult, commandResult);
            // Assert that help command succeeded

            model.updateModelState(helpCommand); // Update state (should not be tracked)

            assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_NO_ROLLBACK);
        } catch (HistoryException e) {
            fail("Failed to update model state", e);
        }
    }


    @Test
    void execute_undoTwice_throwsCommandException() {
        try {
            Person validPerson = new PersonBuilder().build();
            AddCommand addCommand = new AddCommand(validPerson);
            addCommand.execute(model);
            assertEquals(Arrays.asList(validPerson), model.getFilteredPersonList());
            // Assert that add command succeeded

            model.updateModelState(addCommand); // Update state

            CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                    addCommand.getCommandString()));
            Model expectedModel = new ModelManager();

            assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
        } catch (CommandException e) {
            fail("Failed to execute add", e);
        } catch (HistoryException e) {
            fail("Failed to update model state", e);
        }
        assertThrows(CommandException.class, () -> new UndoCommand().execute(model));
    }

}
