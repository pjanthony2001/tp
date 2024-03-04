package seedu.address.history;

import seedu.address.logic.commands.Command;
import seedu.address.model.AddressBook;


/**
 * The `State` class represents a snapshot of the HAL9000 application's state at a specific point in time.
 * It contains information about the executed command and the list of tasks at that time.
 */
public class State {
    private final Command command;
    private final AddressBook addressBook;

    /**
     * Constructs a new State object with the given command and task list.
     *
     * @param command  The command executed to reach this state.
     * @param addressBook The list of tasks at this state.
     */
    public State(Command command, AddressBook addressBook) {
        this.command = command;
        this.addressBook = addressBook;
    }

    /**
     * Gets the list of tasks at this state.
     *
     * @return The list of tasks.
     */
    public AddressBook getAddressBook() {
        return addressBook;
    }

    /**
     * Gets the command executed to reach this state.
     *
     * @return The command.
     */
    public Command getCommand() {
        return command;
    }
}
