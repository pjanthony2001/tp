package seedu.address.history.exceptions;

/**
 * Represents an error which occurs during execution of an invalid history rollback/roll-forward
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
