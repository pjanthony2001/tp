package seedu.address.history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStateTest {
    CommandState commandState = new CommandState("update u/Alex Yeoh p/92647812");

    @Test
    void getCommandText_validCommand_success() {
        assertEquals("update u/Alex Yeoh p/92647812", commandState.getCommandText());
    }
}
