package seedu.address.history;

import java.util.ArrayList;

import seedu.address.history.exceptions.HistoryException;

/**
 * @param <T> The type of state that the HistoryManager class keeps track of
 */
public class HistoryManager<T> implements History<T> {
    protected int currStateIdx;
    protected final ArrayList<T> states;

    /**
     * Constructs a new HistoryManager with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public HistoryManager(T startState) {
        states = new ArrayList<>();
        currStateIdx = 0;
        states.add(startState);
    }

    /**
     * Removes states after the current state, effectively truncating the history.
     */
    private void truncate() {
        boolean isValidIndex = currStateIdx >= 0 && currStateIdx < states.size();
        assert (isValidIndex);
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
     * @throws HistoryException If there are no more future modelStates to roll forward to.
     */
    @Override
    public void rollForwardState() throws HistoryException {
        if (currStateIdx >= states.size() - 1) {
            throw new HistoryException("You can't roll forward the state anymore!");
        }
        currStateIdx += 1;
    }

    /**
     * Adds a new modelState to the history, removing subsequent modelStates.
     *
     * @param state The modelState to add to the history.
     */
    @Override
    public void addState(T state) {
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
    public T getCurrState() {
        return states.get(currStateIdx);
    }
}
