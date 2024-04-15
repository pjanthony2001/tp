package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UndoCommandTest {

    @Test
    public void getCommandString_returnsCommandString() {
        UndoCommand undoCommand = new UndoCommand();
        String expected = UndoCommand.COMMAND_WORD;
        assertEquals(expected, undoCommand.getCommandString());
    }
}
