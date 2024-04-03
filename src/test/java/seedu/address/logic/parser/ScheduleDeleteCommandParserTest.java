package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.HEADING_DESC_MEETING_WITH_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEADING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEADING_MEETING_WITH_ALICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScheduleDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Heading;
class ScheduleDeleteCommandParserTest {

    private final ScheduleDeleteCommandParser parser = new ScheduleDeleteCommandParser();

    @Test
    void parse_allFieldsPresent_success() throws ParseException {
        Heading expectedHeading = new Heading(VALID_HEADING_MEETING_WITH_ALICE);
        assertParseSuccess(parser, HEADING_DESC_MEETING_WITH_ALICE,
                new ScheduleDeleteCommand(expectedHeading));
    }

    @Test
    void parse_missingField_failure() {
        // Missing heading
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_invalidValue_failure() {
        // Invalid heading
        assertParseFailure(parser, INVALID_HEADING_DESC, Heading.MESSAGE_CONSTRAINTS);
    }

}
