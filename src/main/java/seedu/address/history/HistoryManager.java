package seedu.address.history;

import java.util.ArrayList;

import seedu.address.model.Model;


/**
 * The `HistoryManager` class manages the history of commands used in the ConnectCare application.
 * It facilitates the undoing and redoing commands and updating the command history.
 */
public class HistoryManager implements History {
    private int currStateIdx;
    private final ArrayList<State> states;


    /**
     * Constructs a new HistoryManager with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public HistoryManager(State startState) {
        states = new ArrayList<>();
        states.add(startState);
        currStateIdx = 0;
    }

    /**
     * Undoes the last command and restores the previous state.
     * Should be transferred to Command
     * @param model The model system for managing tasks.
     * @return A message indicating the command was undone and the current list of tasks.
     */
    public String undo(Model model) {
        State prevState = getCurrState();
        rollBackState();
        State currState = getCurrState();
        //        model.restoreState(currState);
        // return String.format("Your %s command was undone!\nThis is your current list:\n%s",
        //        prevState.getCommand()
        //        model.displayList());
        return null;
    }

    /**
     * Redoes the last undone command and restores the next state.
     * Transferred to command
     * @param model The model system for managing tasks.
     * @return A message indicating the command was redone and the current list of tasks.
     */
    public String redo(Model model) {
        rollForwardState();
        State currState = getCurrState();
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
        addState(state);
    }

    /**
     * Removes states after the current state, effectively truncating the history.
     */
    private void truncate() {
        //        assert (currStateIdx >= 0 && currStateIdx < states.size() - 1);
        //        states.subList(currStateIdx + 1, states.size()).clear();
    }

    /**
     * Rolls back to the previous state in the history.
     */
    @Override
    public void rollBackState() {
        //        if (currStateIdx == 0) {
        //            throw new Exception();
        //        }
        //        currStateIdx -= 1;
    }

    /**
     * Rolls forward to the next state in the history.
     *
     * @throws Exception If there are no more future states to roll forward to.
     */
    @Override
    public void rollForwardState() {
        //        if (currStateIdx == states.size() - 1) {
        //            throw new Exception("You can't roll forward the state anymore!");
        //        }
        //        currStateIdx += 1;
    }

    /**
     * Adds a new state to the history, removing subsequent states.
     *
     * @param state The state to add to the history.
     */
    @Override
    public void addState(State state) {
        truncate();
        states.add(state);
        currStateIdx += 1;
    }

    /**
     * Gets the current state from the history.
     *
     * @return The current state.
     */
    @Override
    public State getCurrState() {
        return states.get(currStateIdx);
    }
}