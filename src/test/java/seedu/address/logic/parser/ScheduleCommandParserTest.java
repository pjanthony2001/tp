package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ScheduleCommandParserTest {

    private final ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_validAddCommand_returnsScheduleAddCommand() throws ParseException {
        String args1 = " add h/Meeting with Client t/Wednesday, January, 24, 2024 - 09:00 AM "
                + "d/Discuss project details n/John Doe";
        String args2 = " h/Meeting with Client t/Wednesday, January, 24, 2024 - 09:00 AM "
                + "d/Discuss project details n/John Doe";
        ScheduleCommand expectedCommand = new ScheduleAddCommandParser().parse(args2);
        assertEquals(expectedCommand, parser.parse(args1));
    }

    @Test
    public void parse_validDeleteCommand_returnsScheduleDeleteCommand() throws ParseException {
        String args1 = " delete h/Meeting";
        String args2 = " h/Meeting";
        ScheduleCommand expectedCommand = new ScheduleDeleteCommandParser().parse(args2);
        assertEquals(expectedCommand, parser.parse(args1));
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        String invalidArgs = "invalid command";
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }
}
