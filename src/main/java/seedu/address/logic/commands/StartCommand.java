package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Start command for the start state
 */
public class StartCommand extends Command {

    private static StartCommand startCommand = null;

    private StartCommand() {
        super.setTracked(true);
    }

    /**
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if this method is executed
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Start Command should not be executed!");
    }

    /**
     * @return String representation of command
     */
    @Override
    public String getCommandString() {
        return "START COMMAND";
    }

    public static StartCommand getStartCommand() {
        if (startCommand == null) {
            startCommand = new StartCommand();
        }
        return startCommand;
    }
}
