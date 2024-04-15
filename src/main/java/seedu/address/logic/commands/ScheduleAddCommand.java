package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the Calendar.
 */
public class ScheduleAddCommand extends ScheduleCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the schedule.\n"
            + "Parameters: "
            + PREFIX_HEADING + "HEADING "
            + PREFIX_TIME + "TIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_NAME + "CLIENT NAME\n"
            + "Example: schedule " + COMMAND_WORD + " "
            + PREFIX_HEADING + "Meeting with Client "
            + PREFIX_TIME + "2/14/2024 0930 "
            + PREFIX_DESCRIPTION + "Discuss project details "
            + PREFIX_NAME + "John Doe";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";
    private final Event toAdd;

    /**
     * Creates a ScheduleAddCommand to add the specified {@code Event}
     *
     * @param event Event to be added.
     */
    public ScheduleAddCommand(Event event) {
        requireNonNull(event);

        super.setReversible(true);
        toAdd = event;
    }
    /**
     * Executes the ScheduleAddCommand to add the specified event to the model.
     *
     * @param model The model containing the event list.
     * @return The result of the command execution.
     * @throws CommandException If the event to be added already exists in the model.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        assert toAdd != null;
        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }
    /**
     * Checks if this ScheduleAddCommand is equal to another object.
     *
     * @param other The other object to compare.
     * @return True if the other object is a ScheduleAddCommand with the same event, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleAddCommand)) {
            return false;
        }

        ScheduleAddCommand otherAddCommand = (ScheduleAddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }
    /**
     * Returns a string representation of this ScheduleAddCommand.
     *
     * @return A string representation containing the event to be added.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
    /**
     * Gets the command word for this ScheduleAddCommand.
     *
     * @return The command word.
     */
    @Override
    public String getCommandString() {
        return "schedule " + COMMAND_WORD;
    }
}
