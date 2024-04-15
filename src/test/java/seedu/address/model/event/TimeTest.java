package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_HOUSE_CHECKUP_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MEETING_WITH_ALICE;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    public void isValidTimeTest() {
        // null
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("meeting*")); // contains not time
        assertFalse(Time.isValidTime("meeting*")); // contains not time
        assertFalse(Time.isValidTime("meeting*")); // contains not time
        assertFalse(Time.isValidTime("12/2024 1000")); // no day
        assertFalse(Time.isValidTime("4/2024 1000")); // no month
        assertFalse(Time.isValidTime("12/2024 1000")); // no day (int)
        assertFalse(Time.isValidTime("4/12 1000")); // no year
        assertFalse(Time.isValidTime("4/12/2024")); // no time
        assertFalse(Time.isValidTime("4/12/20241000")); // missing space
        assertFalse(Time.isValidTime("4/12/2024 2500")); // missing space
        assertFalse(Time.isValidTime("4/12/2024 2401")); // missing space

        // valid time
        assertTrue(Time.isValidTime("4/12/2024 1000")); // no am/pm
        assertTrue(Time.isValidTime("04/12/2024 1000"));
        assertTrue(Time.isValidTime("04/04/2024 1000"));
        assertTrue(Time.isValidTime("5/2/2024 0900"));
    }
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
