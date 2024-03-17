package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.history.State;
import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes a command
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes the most recent command.";
    public static final String MESSAGE_SUCCESS = "Command undone: %1$s";
    public static final String MESSAGE_NO_ROLLFORWARD = "There is no more history to roll forward!";

    public RedoCommand() {
        setTracked(false);
    }

    /**
     * Undoes the last command and restores the previous state.
     *
     * @param model The model system for managing the address book
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.rollForwardState();
        } catch (HistoryException e) {
            throw new CommandException(MESSAGE_NO_ROLLFORWARD);
        }

        State currState = model.getCurrentState();
        model.restoreState(currState);

        return new CommandResult(String.format(MESSAGE_SUCCESS, currState.getCommand().getCommandString()));
    }
    @Override
    public String getCommandString() {
        return COMMAND_WORD;
    }
}

