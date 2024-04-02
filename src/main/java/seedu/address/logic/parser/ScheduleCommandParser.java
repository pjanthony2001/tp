package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.ScheduleAddCommand;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.ScheduleDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.Heading;
import seedu.address.model.event.Time;
import seedu.address.model.person.Description;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_HEADING, PREFIX_TIME, PREFIX_DESCRIPTION);
        // add
        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_HEADING, PREFIX_TIME, PREFIX_DESCRIPTION)) {
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_HEADING, PREFIX_TIME, PREFIX_DESCRIPTION)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ScheduleAddCommand.MESSAGE_USAGE));
            }
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_HEADING, PREFIX_TIME, PREFIX_DESCRIPTION);

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Heading heading = ParserUtil.parseHeading(argMultimap.getValue(PREFIX_HEADING).get());
            Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
            Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

            Event event = new Event(heading, time, description, name);
            return new ScheduleAddCommand(event);
        }
        // delete
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
