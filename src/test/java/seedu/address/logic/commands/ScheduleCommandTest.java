package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class ScheduleCommandTest {
    @Test
    void checkCommandWord_success() {
        assertTrue(ScheduleCommand.COMMAND_WORD.equals("schedule"));
    }

    @Test
    void checkCommandWord_failure() {
        assertFalse(ScheduleCommand.COMMAND_WORD.equals("scheduleAdd"));
    }
}
