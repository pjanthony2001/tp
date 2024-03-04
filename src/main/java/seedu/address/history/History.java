package seedu.address.history;

/**
 * The `History` class manages the history of states in the HAL9000 application.
 * It allows for rolling back and rolling forward to previous states.
 */
public interface History {
    /**
     * Rolls back to the previous state in the history.
     *
     */
    void rollBackState() throws Exception;

    /**
     * Rolls forward to the next state in the history.
     *
     * @throws Exception If there are no more future states to roll forward to.
     */
    void rollForwardState() throws Exception;

    /**
     * Adds a new state to the history, removing subsequent states.
     *
     * @param state The state to add to the history.
     */
    void addState(State state);

    /**
     * Gets the current state from the history.
     *
     * @return The current state.
     */
    State getCurrState();
}
