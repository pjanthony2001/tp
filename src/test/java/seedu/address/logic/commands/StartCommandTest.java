package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class StartCommandTest {
    private Model model = new ModelManager();
    @Test
    public void execute_throwsCommandException() {
        assertThrows(CommandException.class, () -> StartCommand.getStartCommand().execute(model));
    }

    @Test
    public void getCommandString_returnsCommandString() {
        StartCommand startCommand = StartCommand.getStartCommand();
        String expected = StartCommand.COMMAND_WORD;
        assertEquals(expected, startCommand.getCommandString());
    }
}
