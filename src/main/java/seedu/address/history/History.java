package seedu.address.history;

import java.util.ArrayList;

/**
 * The `History` class manages the history of states in the HAL9000 application.
 * It allows for rolling back and rolling forward to previous states.
 */
public class History {
    private int currStateIdx;
    private final ArrayList<State> states;

    /**
     * Constructs a new History object with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public History(State startState) {
        states = new ArrayList<>();
        states.add(startState);
        currStateIdx = 0;
    }

    /**
     * Removes states after the current state, effectively truncating the history.
     */
    private void truncate() {
        assert (currStateIdx >= 0 && currStateIdx < states.size() - 1);
        states.subList(currStateIdx + 1, states.size()).clear();
    }

    /**
     * Rolls back to the previous state in the history.
     *
     */
    public void rollBackState() throws Exception {
        if (currStateIdx == 0) {
            throw new Exception();
        }
        currStateIdx -= 1;
    }

    /**
     * Rolls forward to the next state in the history.
     *
     * @throws Exception If there are no more future states to roll forward to.
     */
    public void rollForwardState() throws Exception {
        if (currStateIdx == states.size() - 1) {
            throw new Exception("You can't roll forward the state anymore!");
        }
        currStateIdx += 1;
    }

    /**
     * Adds a new state to the history, removing subsequent states.
     *
     * @param state The state to add to the history.
     */
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
    public State getCurrState() {
        return states.get(currStateIdx);
    }
}
