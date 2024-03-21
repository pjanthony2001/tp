package seedu.address.history;

import java.util.ArrayList;

import seedu.address.history.exceptions.HistoryException;


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
     * Removes states after the current state, effectively truncating the history.
     */
    private void truncate() {
        assert (currStateIdx >= 0 && currStateIdx < states.size());
        states.subList(currStateIdx + 1, states.size()).clear();
    }

    /**
     * Rolls back to the previous state in the history.
     */
    @Override
    public void rollBackState() throws HistoryException {
        if (currStateIdx == 0) {
            throw new HistoryException("You can't rollback the state anymore!");
        }
        currStateIdx -= 1;
    }

    /**
     * Rolls forward to the next state in the history.
     *
     * @throws Exception If there are no more future states to roll forward to.
     */
    @Override
    public void rollForwardState() throws HistoryException {
        if (currStateIdx == states.size() - 1) {
            throw new HistoryException("You can't roll forward the state anymore!");
        }
        currStateIdx += 1;
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
