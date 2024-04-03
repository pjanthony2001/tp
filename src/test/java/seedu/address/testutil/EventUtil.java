package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.ScheduleAddCommand;
import seedu.address.model.event.Event;

/**
 * A utility class for Person.
 */
public class EventUtil {

    /**
     * Returns a schedule add command string for adding the {@code event}.
     */
    public static String getScheduleAddCommand(Event event) {
        return ScheduleAddCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getClientName().fullName + " ");
        sb.append(PREFIX_HEADING + event.getHeading().heading + " ");
        sb.append(PREFIX_TIME + event.getTime().time + " ");
        return sb.toString();
    }
}
