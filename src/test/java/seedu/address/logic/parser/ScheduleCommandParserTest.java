package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.Assert;


public class ScheduleCommandParserTest {

    private final ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_validAddCommand_returnsScheduleAddCommand() throws ParseException {
        String args1 = " add h/Meeting with Client t/2/14/2024 0930 "
                + "d/Discuss project details n/John Doe";
        String args2 = " h/Meeting with Client t/2/14/2024 0930 "
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

    @Test
    public void parse_invalidFormat_throwsParseException() {
        Assert.assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ScheduleCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

}
