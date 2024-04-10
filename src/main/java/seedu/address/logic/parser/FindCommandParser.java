package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.DescriptionContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.KinContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    @Override
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || trimmedArgs.length() <= 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_NOK,
            PREFIX_DESCRIPTION, PREFIX_TAG);
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        List<String> phoneKeywords = argMultimap.getAllValues(PREFIX_PHONE);
        List<String> emailKeywords = argMultimap.getAllValues(PREFIX_EMAIL);
        List<String> addressKeywords = argMultimap.getAllValues(PREFIX_ADDRESS);
        List<String> kinKeywords = argMultimap.getAllValues(PREFIX_NOK);
        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        List<String> descriptionKeywords = argMultimap.getAllValues(PREFIX_DESCRIPTION);

        checkForNulls(nameKeywords, phoneKeywords, emailKeywords,
            addressKeywords, tagKeywords, kinKeywords, descriptionKeywords);

        return new FindCommand(new NameContainsKeywordsPredicate(nameKeywords),
                new PhoneContainsKeywordsPredicate(phoneKeywords),
                new AddressContainsKeywordsPredicate(addressKeywords),
                new EmailContainsKeywordsPredicate(emailKeywords),
                new TagContainsKeywordsPredicate(tagKeywords),
                new KinContainsKeywordsPredicate(kinKeywords),
                new DescriptionContainsKeywordsPredicate(descriptionKeywords));
    }

    private void checkForNulls(List<String>... lists) throws ParseException {
        boolean hasEmptyString = Arrays.stream(lists)
            .flatMap(List::stream)
            .map(String::trim)
            .anyMatch(String::isEmpty);

        if (hasEmptyString) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
