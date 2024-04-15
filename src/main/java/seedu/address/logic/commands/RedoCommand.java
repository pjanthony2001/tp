package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.history.ModelState;
import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes a command, reverting the model to that state
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes the most recent command.";
    public static final String MESSAGE_SUCCESS = "Command redone: %1$s";
    public static final String MESSAGE_NO_ROLLFORWARD = "There is no more history to roll forward!";

    public RedoCommand() {
        setReversible(false);
    }

    /**
     * Redoes the last command and restores the previous state.
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

        ModelState currModelState = model.getCurrentModelState();
        model.restoreModelState(currModelState);

        return new CommandResult(String.format(MESSAGE_SUCCESS, currModelState.getCommand().getCommandString()));
    }
    @Override
    public String getCommandString() {
        return COMMAND_WORD;
    }
}

