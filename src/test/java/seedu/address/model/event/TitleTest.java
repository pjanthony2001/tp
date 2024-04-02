package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid title
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(Title.isValidTitle("meeting*")); // contains non-alphanumeric characters
        assertFalse(Title.isValidTitle("Meeting MEETING meeting meeting with MEETING "
                       + "MEETING MEETING man MEETING MEETING MEETING MEETING MEETING MEETING MEETIMG")); // too long

        // valid title
        assertTrue(Title.isValidTitle("meeting meeting")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("meeting the 2nd")); // alphanumeric characters
        assertTrue(Title.isValidTitle("Capital Meeting with Tan")); // with capital letters
        assertTrue(Title.isValidTitle("Meeting MEETING meeting meeting with MEETING MEETING MEETING man"));
        // long titles
    }

    @Test
    public void equals() {
        Title title = new Title("Valid Title");

        // same values -> returns true
        assertTrue(title.equals(new Title("Valid Title")));

        // same object -> returns true
        assertTrue(title.equals(title));

        // null -> returns false
        assertFalse(title.equals(null));

        // different types -> returns false
        assertFalse(title.equals(5.0f));

        // different values -> returns false
        assertFalse(title.equals(new Title("Other Valid Title")));
    }
}
