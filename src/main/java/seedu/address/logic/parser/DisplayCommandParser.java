package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.DisplayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
/**
 * Parses input arguments and creates a new DisplayCommand object.
 * A DisplayCommandParser object is responsible for parsing user input into a DisplayCommand,
 * which displays persons whose names contain specified keywords.
 */

public class DisplayCommandParser implements Parser<DisplayCommand> {
    /**
     * Parses the given input arguments and creates a new DisplayCommand object.
     *
     * @param args The input arguments to parse.
     * @return A DisplayCommand object representing the parsed command.
     * @throws ParseException If the input arguments cannot be parsed.
     */
    public DisplayCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new DisplayCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
