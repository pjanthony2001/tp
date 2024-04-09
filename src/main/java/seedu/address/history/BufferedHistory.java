package seedu.address.history;

import seedu.address.history.exceptions.HistoryException;

/**
 * The `BufferedHistory` class manages the history of states in the ConnectCare application, with a buffer.
 * It allows for rolling back and rolling forward to previous states.
 */
public interface BufferedHistory<T> extends History<T> {
    public T getCurrStateHasBuffer() throws HistoryException;
}
