package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;

import java.util.stream.Stream;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.ScheduleDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Heading;

/**
 * Parses input arguments and creates a new {@code ScheduleDeleteCommand} object.
 */
public class ScheduleDeleteCommandParser implements Parser<ScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code ScheduleDeleteCommand}
     * and returns a {@code ScheduleDeleteCommand} object for execution.
     *
     * @param args The arguments provided by the user.
     * @return A {@code ScheduleDeleteCommand} object representing the delete command.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public ScheduleDeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HEADING);
        if (!arePrefixesPresent(argMultimap, PREFIX_HEADING) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleDeleteCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_HEADING);

        Heading heading = ParserUtil.parseHeading(argMultimap.getValue(PREFIX_HEADING).get());
        return new ScheduleDeleteCommand(heading);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
