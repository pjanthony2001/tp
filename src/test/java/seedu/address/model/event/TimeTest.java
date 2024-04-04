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
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("meeting*")); // contains not time
        assertFalse(Time.isValidTime("meeting*")); // contains not time
        assertFalse(Time.isValidTime("meeting*")); // contains not time
        assertFalse(Time.isValidTime("April, 12, 2024 - 10:00 AM")); // no day
        assertFalse(Time.isValidTime("Friday, 12, 2024 - 10:00 AM")); // no month
        assertFalse(Time.isValidTime("Friday, April,2024 - 10:00 AM")); //no day (int)
        assertFalse(Time.isValidTime("Friday, April, 12 - 10:00 AM")); // no year
        assertFalse(Time.isValidTime("Friday, April, 12, 2024 AM")); // no time
        assertFalse(Time.isValidTime("Friday, April, 12, 2024 - 10:00")); // no am/pm
        assertFalse(Time.isValidTime("Friday, April, 12, 2024 -10:00 AM")); //missing space
        assertFalse(Time.isValidTime("Friday, April, 12, 2024  10:00 AM")); //missing hyphen
        assertFalse(Time.isValidTime("Friday, April 12 2024 - 10:00 AM")); //missing comma

        assertTrue(Time.isValidTime("Friday, April, 12, 2024 - 10:00 AM"));
        assertTrue(Time.isValidTime("Thursday, April, 04, 2024 - 10:00 AM"));
        assertTrue(Time.isValidTime("Thursday, May, 02, 2024 - 09:00 AM"));
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
