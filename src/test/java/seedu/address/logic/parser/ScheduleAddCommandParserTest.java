package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_NAME_DESC_MEETING_WITH_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MEETING_WITH_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEADING_DESC_MEETING_WITH_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEADING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_MEETING_WITH_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEADING_MEETING_WITH_ALICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ScheduleAddCommand;
import seedu.address.logic.parser.ScheduleAddCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.Heading;
import seedu.address.model.person.Description;
import seedu.address.model.person.Name;
import seedu.address.model.event.Time;
import seedu.address.testutil.EventBuilder;

public class ScheduleAddCommandParserTest {

    private final ScheduleAddCommandParser parser = new ScheduleAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Event expectedEvent = new EventBuilder(MEETING_WITH_ALICE).build();

        assertParseSuccess(parser, NAME_DESC_AMY + HEADING_DESC_MEETING_WITH_ALICE
                + TIME_DESC_MEETING_WITH_ALICE + DESCRIPTION_DESC_MEETING_WITH_ALICE,
                new ScheduleAddCommand(expectedEvent));
    }

    @Test
    public void parse_missingField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleAddCommand.MESSAGE_USAGE);

        // Missing heading
        String argsMissingHeading = NAME_DESC_AMY + TIME_DESC_MEETING_WITH_ALICE
                + DESCRIPTION_DESC_MEETING_WITH_ALICE + CLIENT_NAME_DESC_MEETING_WITH_ALICE;
        assertParseFailure(parser, argsMissingHeading, expectedMessage);

        // Missing time
        String argsMissingTime = NAME_DESC_AMY + HEADING_DESC_MEETING_WITH_ALICE
                + DESCRIPTION_DESC_MEETING_WITH_ALICE + CLIENT_NAME_DESC_MEETING_WITH_ALICE;
        assertParseFailure(parser, argsMissingTime, expectedMessage);

        // Missing description
        String argsMissingDescription = NAME_DESC_AMY + HEADING_DESC_MEETING_WITH_ALICE + TIME_DESC_MEETING_WITH_ALICE
                + CLIENT_NAME_DESC_MEETING_WITH_ALICE;
        assertParseFailure(parser, argsMissingDescription, expectedMessage);

        // Missing client name
        String argsMissingClientName = HEADING_DESC_MEETING_WITH_ALICE + TIME_DESC_MEETING_WITH_ALICE
                + DESCRIPTION_DESC_MEETING_WITH_ALICE;
        assertParseFailure(parser, argsMissingClientName, expectedMessage);
    }

    @Test
    public void parse_repeatedField_failure() {
        String validExpectedEventString = HEADING_DESC_MEETING_WITH_ALICE + DESCRIPTION_DESC_MEETING_WITH_ALICE
                + TIME_DESC_MEETING_WITH_ALICE + CLIENT_NAME_DESC_MEETING_WITH_ALICE;

        // multiple names
        assertParseFailure(parser, CLIENT_NAME_DESC_MEETING_WITH_ALICE + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple times
        assertParseFailure(parser, TIME_DESC_MEETING_WITH_ALICE + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));

        // multiple headings
        assertParseFailure(parser, HEADING_DESC_MEETING_WITH_ALICE + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HEADING));

        // multiple descriptions
        assertParseFailure(parser, DESCRIPTION_DESC_MEETING_WITH_ALICE + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid heading
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_HEADING_DESC + TIME_DESC_MEETING_WITH_ALICE
                + DESCRIPTION_DESC_MEETING_WITH_ALICE , Heading.MESSAGE_CONSTRAINTS);

        // Invalid time
        assertParseFailure(parser, NAME_DESC_AMY + HEADING_DESC_MEETING_WITH_ALICE + INVALID_TIME_DESC
                + DESCRIPTION_DESC_MEETING_WITH_ALICE , Time.MESSAGE_CONSTRAINTS);

        // Invalid description
        assertParseFailure(parser, NAME_DESC_AMY + HEADING_DESC_MEETING_WITH_ALICE + TIME_DESC_MEETING_WITH_ALICE
                + INVALID_DESCRIPTION_DESC , Description.MESSAGE_CONSTRAINTS);

        // Invalid client name
        assertParseFailure(parser, INVALID_NAME_DESC + HEADING_DESC_MEETING_WITH_ALICE + TIME_DESC_MEETING_WITH_ALICE
                + DESCRIPTION_DESC_MEETING_WITH_ALICE , Name.MESSAGE_CONSTRAINTS);
    }
}
