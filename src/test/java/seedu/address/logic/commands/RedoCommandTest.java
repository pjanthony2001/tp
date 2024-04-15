package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RedoCommandTest {

    @Test
    public void getCommandString_returnsCommandString() {
        RedoCommand redoCommand = new RedoCommand();
        String expected = RedoCommand.COMMAND_WORD;
        assertEquals(expected, redoCommand.getCommandString());
    }
}
