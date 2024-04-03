package seedu.address.history;

public class CommandState {
    private final String commandText;

    public CommandState(String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }
}
