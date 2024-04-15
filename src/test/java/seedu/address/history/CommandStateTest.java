package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandStateTest {
    private final CommandState commandState = new CommandState("update u/Alex Yeoh p/92647812");

    @Test
    void getCommandText_validCommand_success() {
        assertEquals("update u/Alex Yeoh p/92647812", commandState.getCommandText());
    }
}
