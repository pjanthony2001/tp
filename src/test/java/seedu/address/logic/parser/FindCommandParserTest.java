package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.DescriptionContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.KinContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_prefixEmptyArg_throwsParseException() {
        assertParseFailure(parser, " n/", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgWithPrefix_throwsParseException() {
        assertParseFailure(parser, " n/ a/ k/", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_argNoPrefix_throwsParseException() {
        assertParseFailure(parser, "alex", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_expectedCommand_success() throws ParseException {
        NameContainsKeywordsPredicate namePred = new NameContainsKeywordsPredicate(
            Arrays.asList("alex yeoh"));
        PhoneContainsKeywordsPredicate phonePred = new PhoneContainsKeywordsPredicate(
            Arrays.asList("123"));
        AddressContainsKeywordsPredicate addressPred = new AddressContainsKeywordsPredicate(
            Arrays.asList("clementi"));
        TagContainsKeywordsPredicate tagPred = new TagContainsKeywordsPredicate(
            Arrays.asList());
        KinContainsKeywordsPredicate kinPred = new KinContainsKeywordsPredicate(
            Arrays.asList());
        EmailContainsKeywordsPredicate emailPred = new EmailContainsKeywordsPredicate(
            Arrays.asList());
        DescriptionContainsKeywordsPredicate descriptionPred = new DescriptionContainsKeywordsPredicate(
            Arrays.asList());

        assertParseSuccess(parser, " n/alex yeoh p/123 a/clementi" ,
            new FindCommand(namePred, phonePred, addressPred, emailPred, tagPred, kinPred, descriptionPred));
    }
}
