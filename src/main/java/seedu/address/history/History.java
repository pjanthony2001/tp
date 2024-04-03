package seedu.address.history;

import seedu.address.history.exceptions.HistoryException;

/**
 * The `History` class manages the history of states in the ConnectCare application.
 * It allows for rolling back and rolling forward to previous states.
 */
public interface History<T> {
    /**
     * Rolls back to the previous state in the history.
     *
     */
    void rollBackState() throws HistoryException;

    /**
     * Rolls forward to the next state in the history.
     *
     * @throws Exception If there are no more future states to roll forward to.
     */
    void rollForwardState() throws HistoryException;

    /**
     * Adds a new T state to the history, removing subsequent states.
     *
     * @param state The state to add to the history.
     */
    void addState(T state);

    /**
     * Gets the current state from the history.
     *
     * @return The current state.
     */
    T getCurrState();

    T getCurrStateHasBuffer() throws HistoryException;
}
