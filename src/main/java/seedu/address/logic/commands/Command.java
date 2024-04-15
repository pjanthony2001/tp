package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    private boolean isReversible = false;

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Returns the string representation of the command for History saving purposes.
     *
     * @return The string representation of command.
     */
    public abstract String getCommandString();

    /**
     * Returns if the command is reversible or not. By default, it is not reversible.
     *
     * @return true if the command is reversible and false otherwise.
     */
    public boolean isReversible() {
        return isReversible;
    }

    /**
     * Sets if the command is reversible or not. By default, it is not reversible.
     *
     * @param isReversible The boolean representing if the command is reversible or not.
     */
    public void setReversible(boolean isReversible) {
        this.isReversible = isReversible;
    }
}
