package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class HeadingTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Heading(null));
    }

    @Test
    public void constructor_invalidHeading_throwsIllegalArgumentException() {
        String invalidHeading = "";
        assertThrows(IllegalArgumentException.class, () -> new Heading(invalidHeading));
    }

    @Test
    public void isValidHeadingTest() {
        // null title
        assertThrows(NullPointerException.class, () -> Heading.isValidHeading(null));

        // invalid title
        assertFalse(Heading.isValidHeading("")); // empty string
        assertFalse(Heading.isValidHeading(" ")); // spaces only
        assertFalse(Heading.isValidHeading("^")); // only non-alphanumeric characters
        assertFalse(Heading.isValidHeading("meeting*")); // contains non-alphanumeric characters
        assertFalse(Heading.isValidHeading("Meeting MEETING meeting meeting with MEETING "
                       + "MEETING MEETING man MEETING MEETING MEETING MEETING MEETING MEETING MEETIMG")); // too long

        // valid title
        assertTrue(Heading.isValidHeading("meeting meeting")); // alphabets only
        assertTrue(Heading.isValidHeading("12345")); // numbers only
        assertTrue(Heading.isValidHeading("meeting the 2nd")); // alphanumeric characters
        assertTrue(Heading.isValidHeading("Capital Meeting with Tan")); // with capital letters
        assertTrue(Heading.isValidHeading("Meeting MEETING meeting meeting with MEETING MEETING MEETING man"));
        // long titles
    }

    @Test
    public void equals() {
        Heading heading = new Heading("Valid Heading");

        // same values -> returns true
        assertTrue(heading.equals(new Heading("Valid Heading")));

        // same object -> returns true
        assertTrue(heading.equals(heading));

        // null -> returns false
        assertFalse(heading.equals(null));

        // different types -> returns false
        assertFalse(heading.equals(5.0f));

        // different values -> returns false
        assertFalse(heading.equals(new Heading("Other Valid Heading")));
    }
}
