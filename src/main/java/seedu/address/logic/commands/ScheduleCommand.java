package seedu.address.logic.commands;

/**
 * Adds a person to the address book.
 */
public abstract class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = "If adding to the schedule use:\n"
            + ScheduleAddCommand.MESSAGE_USAGE + "\n"
            + "If deleting from the schedule use:\n"
            + ScheduleDeleteCommand.MESSAGE_USAGE;
}
