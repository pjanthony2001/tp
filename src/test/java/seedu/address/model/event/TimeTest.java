package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_HOUSE_CHECKUP_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MEETING_WITH_ALICE;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

class TimeTest {

    // @Rishit add isValidTime checks here
    @Test
    void equals() {
        Time time = new Time(VALID_TIME_MEETING_WITH_ALICE);

        // same values -> returns true
        assertTrue(time.equals(new Time(VALID_TIME_MEETING_WITH_ALICE)));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals(5.0f));

        // different values -> returns false
        assertFalse(time.equals(new Time(VALID_TIME_HOUSE_CHECKUP_BENSON)));

        // Second Constructor
        time = new Time(LocalDateTime.of(2024, Month.APRIL, 10, 2, 10));

        // same values -> returns true
        assertTrue(time.equals(new Time(LocalDateTime.of(2024, Month.APRIL, 10, 2, 10))));

        // different values -> returns false
        assertFalse(time.equals(new Time(LocalDateTime.of(2024, Month.MARCH, 11, 2, 10))));
    }
}
