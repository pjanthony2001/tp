package seedu.address.history;

import seedu.address.model.Model;

/**
 * The `HistoryManager` class manages the history of commands used in the ConnectCare application.
 * It facilitates the undoing and redoing commands and updating the command history.
 */
public class HistoryManager {
    private final History history;

    /**
     * Constructs a new HistoryManager with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public HistoryManager(State startState) {
        history = new History(startState);
    }

    /**
     * Undoes the last command and restores the previous state.
     *
     * @param model The model system for managing tasks.
     * @return A message indicating the command was undone and the current list of tasks.
     */
    public String undo(Model model) throws Exception {
        State prevState = history.getCurrState();
        history.rollBackState();
        State currState = history.getCurrState();
        //        model.restoreState(currState);
        // return String.format("Your %s command was undone!\nThis is your current list:\n%s",
        //        prevState.getCommand()
        //        model.displayList());
        return null;
    }

    /**
     * Redoes the last undone command and restores the next state.
     *
     * @param model The model system for managing tasks.
     * @return A message indicating the command was redone and the current list of tasks.
     */
    public String redo(Model model) throws Exception {
        history.rollForwardState();
        State currState = history.getCurrState();
        // model.restoreState(currState);
        // return String.format("Your %s command was redone!\nThis is your current list:\n%s",
        //      currState.getCommand(),
        //      model.displayList());
        return null;
    }

    /**
     * Updates the history with a new state, if necessary.
     *
     * @param state The new state to be added to the history.
     */
    public void updateHistory(State state) {
        //        if (state.isIgnoredHistory()) {
        //            return;
        //        }
        history.addState(state);
    }
}
