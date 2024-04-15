package seedu.address.history;

import seedu.address.history.exceptions.HistoryException;

/**
 * @param <T> The type of state that the abstract class keeps track of
 */
public class BufferedHistoryManager<T> extends HistoryManager<T> implements BufferedHistory<T> {

    /**
     * Constructs a new HistoryManager with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public BufferedHistoryManager(T startState) {
        super(startState);
    }

    @Override
    public void addState(T state) {
        pullForwardPointer();
        int lastIndex = states.size() - 1;
        T buffer = states.get(lastIndex);
        states.remove(lastIndex);
        states.add(state);
        states.add(buffer);
        currStateIdx++;
    }

    private void pullForwardPointer() {
        currStateIdx = states.size() - 1;
    }

    @Override
    public T getCurrStateHasBuffer() throws HistoryException {
        if (currStateIdx == states.size() - 1) {
            throw new HistoryException("Cannot read from buffer");
        }
        return states.get(currStateIdx);
    }
}
