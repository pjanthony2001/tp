package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.HistoryUtil.TYPICAL_START_STATE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoryManagerTest {
    private HistoryManager history;
    @BeforeEach
    void setup() {
        history = new HistoryManager(TYPICAL_START_STATE);
    }

    @Test
    void undo() {
        history.undo(null);
    }

    @Test
    void redo() {
        history.redo(null);
    }

    @Test
    void updateHistory() {
        history.updateHistory(TYPICAL_START_STATE);
    }

    @Test
    void rollBackState() {
        history.rollBackState();
    }

    @Test
    void rollForwardState() {
        history.rollForwardState();
    }

    @Test
    void addState() {
        history.addState(TYPICAL_START_STATE);
    }

    @Test
    void getCurrState() {
        State state = history.getCurrState();
        assertEquals(state, TYPICAL_START_STATE);
    }
}
