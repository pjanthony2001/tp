package seedu.address.testutil;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command Utility that can be used for testing, especially for History and State
 */
public class CommandUtil {
    private static final CommandStub COMMAND_STUB_TRACKED = new CommandStubTracked();
    /**
     * Command stub to be used for testing
     */
    private static class CommandStub extends Command {
        @Override
        public CommandResult execute(Model model) throws CommandException {
            return null;
        }

        @Override
        public String getCommandString() {
            return "";
        }
    }

    private static class CommandStubTracked extends CommandStub {
        public CommandStubTracked() {
            super.setTracked(true);
        }
    }

    public static Command getCommandStub() {
        return COMMAND_STUB_TRACKED;
    }
}
