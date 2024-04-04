package seedu.address.history;

/**
 * The `CommandState` class represents commands entered by the user that are executed successfully.
 */
public class CommandState {
    private final String commandText;

    public CommandState(String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }
}
