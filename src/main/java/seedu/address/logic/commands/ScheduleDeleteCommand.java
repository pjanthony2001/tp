package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.Heading;

/**
 * Deletes an event identified by its heading from the schedule.
 */
public class ScheduleDeleteCommand extends ScheduleCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by its heading from the schedule.\n"
            + "Parameters: h/HEADING\n"
            + "Example: schedule " + COMMAND_WORD + " " + PREFIX_HEADING + "Meeting with Client";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %1$s";

    private final Heading heading;

    /**
     * Constructs a ScheduleDeleteCommand to delete the event with the specified heading.
     *
     * @param heading The heading of the event to be deleted.
     */
    public ScheduleDeleteCommand(Heading heading) {
        super.setReversible(true);
        this.heading = heading;
    }
    /**
     * Executes the ScheduleDeleteCommand to delete the event with the specified heading.
     *
     * @param model The model containing the event list.
     * @return The result of the command execution.
     * @throws CommandException If the specified event heading is invalid or if multiple events have the same heading.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Event> eventsList = model.getEventList();
        List<Event> sameHeadings = eventsList.stream()
                .filter(event -> event.getHeading().equals(heading))
                .collect(Collectors.toList());
        // No matching headings
        if (sameHeadings.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_HEADING);
        }
        Event eventToDelete = sameHeadings.get(0);
        model.deleteEvent(eventToDelete); // delete event
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, Messages.format(eventToDelete)));
    }
    /**
     * Checks if this ScheduleDeleteCommand is equal to another object.
     *
     * @param other The other object to compare.
     * @return True if the other object is a ScheduleDeleteCommand with the same heading, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleDeleteCommand)) {
            return false;
        }

        ScheduleDeleteCommand otherDeleteCommand = (ScheduleDeleteCommand) other;
        return heading.equals(otherDeleteCommand.heading);
    }
    /**
     * Returns a string representation of this ScheduleDeleteCommand.
     *
     * @return A string representation containing the heading of the event to be deleted.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("heading", heading)
                .toString();
    }
    /**
     * Gets the command word for this ScheduleDeleteCommand.
     *
     * @return The command word.
     */
    @Override
    public String getCommandString() {
        return "schedule " + COMMAND_WORD;
    }
}
