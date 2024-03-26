package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.HistoryUtil.TYPICAL_SECOND_STATE;
import static seedu.address.testutil.HistoryUtil.TYPICAL_START_STATE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.history.exceptions.HistoryException;

class HistoryManagerTest {
    private HistoryManager history;

    @BeforeEach
    void setup() {
        history = new HistoryManager(TYPICAL_START_STATE);
    }

    @Test
    void rollBackStateFailure() {
        assertThrows(HistoryException.class, () -> history.rollBackState());
    }
    @Test
    void rollForwardStateFailure() {
        assertThrows(HistoryException.class, () -> history.rollForwardState());
    }
    @Test
    void getCurrStateTest() {
        State state = history.getCurrState();
        assertEquals(state, TYPICAL_START_STATE);
    }
    @Test
    void addStateTest() {
        history.addState(TYPICAL_SECOND_STATE);
        assertEquals(history.getCurrState(), TYPICAL_SECOND_STATE);
    }
    @Test
    void rollBackStateSuccess() {
        history.addState(TYPICAL_SECOND_STATE);
        assertDoesNotThrow(() -> history.rollBackState());
    }

}
