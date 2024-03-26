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
    void rollBackState_noPreviousState_exceptionThrown() {
        assertThrows(HistoryException.class, () -> history.rollBackState());
    }
    @Test
    void rollForwardState_noNextState_exceptionThrown() {
        assertThrows(HistoryException.class, () -> history.rollForwardState());
    }
    @Test
    void getCurrState_typicalStartState_successfullyReturnsStartState() {
        State state = history.getCurrState();
        assertEquals(state, TYPICAL_START_STATE);
    }
    @Test
    void addState_typicalSecondState_successfullyReturnsSecondState() {
        history.addState(TYPICAL_SECOND_STATE);
        assertEquals(history.getCurrState(), TYPICAL_SECOND_STATE);
    }
    @Test
    void rollBackState_typicalSecondState_doesNotThrowExceptions() {
        history.addState(TYPICAL_SECOND_STATE);
        assertDoesNotThrow(() -> history.rollBackState());
    }

}
