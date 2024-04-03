package seedu.address.history;

import java.util.ArrayList;

import seedu.address.history.exceptions.HistoryException;

/**
 * @param <T> The type of state that the abstract class keeps track of
 */
public class HistoryManager<T> implements History<T> {
    private int currStateIdx;
    private final ArrayList<T> states;
    private boolean hasBuffer;

    /**
     * Constructs a new HistoryManager with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public HistoryManager(T startState, boolean hasBuffer) {
        states = new ArrayList<>();
        currStateIdx = 0;
        states.add(startState);
        this.hasBuffer = hasBuffer;
    }

    /**
     * Removes modelStates after the current state, effectively truncating the history.
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
     * @throws HistoryException If there are no more future modelStates to roll forward to.
     */
    @Override
    public void rollForwardState() throws HistoryException {
        int boundary = hasBuffer ? states.size() - 2 : states.size() - 1;
        if (currStateIdx >= states.size() - 1) {
            throw new HistoryException("You can't roll forward the state anymore!");
        }
        currStateIdx += 1;
        System.out.println(currStateIdx);
    }

    /**
     * Adds a new modelState to the history, removing subsequent modelStates.
     *
     * @param state The modelState to add to the history.
     */
    @Override
    public void addState(T state) {
        if (hasBuffer) {
            pullForwardPointer();
            T buffer = states.get(states.size() - 1);
            states.remove(states.size() - 1);
            states.add(state);
            states.add(buffer);
            currStateIdx++;
        } else {
            truncate();
            states.add(state);
            currStateIdx += 1;
        }
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

    private void pullForwardPointer() {
        currStateIdx = states.size() - 1;
    }

    @Override
    public T getCurrStateHasBuffer() throws HistoryException {
        if (hasBuffer && currStateIdx == states.size() - 1) {
            throw new HistoryException("Cannot read from buffer");
        }
        return states.get(currStateIdx);
    }
}
