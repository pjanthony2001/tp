package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;
import static seedu.address.testutil.TypicalEvents.HOUSE_CHECKUP_BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void equals() {
        // same values -> returns true
        Event meetingWithAliceCopy = new EventBuilder().build();
        assertTrue(MEETING_WITH_ALICE.equals(meetingWithAliceCopy));

        // same object -> returns true
        assertTrue(MEETING_WITH_ALICE.equals(MEETING_WITH_ALICE));

        // null -> returns false
        assertFalse(MEETING_WITH_ALICE.equals(null));

        // different type -> returns false
        assertFalse(MEETING_WITH_ALICE.equals(5));

        // different event -> returns false
        assertFalse(MEETING_WITH_ALICE.equals(HOUSE_CHECKUP_BENSON));

        // different name -> returns false
        Event updatedAlice = new EventBuilder(MEETING_WITH_ALICE).withTitle().build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));

        // different phone -> returns false
        updatedAlice = new EventBuilder(MEETING_WITH_ALICE).withLocalDateTime().build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));

        // different email -> returns false
        updatedAlice = new EventBuilder(MEETING_WITH_ALICE).withClientName(VALID_NAME_BOB).build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));

        // different description -> returns false
        updatedAlice = new EventBuilder(MEETING_WITH_ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Event.class.getCanonicalName() + "{name=" + MEETING_WITH_ALICE.getName() + ", phone=" + MEETING_WITH_ALICE.getPhone()
                + ", email=" + MEETING_WITH_ALICE.getEmail() + ", address=" + MEETING_WITH_ALICE.getAddress() + ", description="
                + MEETING_WITH_ALICE.getDescription() + ", nextOfKin=" + MEETING_WITH_ALICE.getNextOfKin() + ", tags=" + MEETING_WITH_ALICE.getTags() + "}";
        assertEquals(expected, MEETING_WITH_ALICE.toString());
    }
}
