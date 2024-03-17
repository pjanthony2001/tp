package seedu.address.history.exceptions;

/**
 * Represents an error which occurs during execution of a history rollback
 */
public class HistoryException extends Exception {

    public HistoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code HistoryException} with the specified detail {@code message} and {@code cause}.
     */
    public HistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
