package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_HOUSE_CHECKUP_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOUSE_CHECKUP_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEADING_HOUSE_CHECKUP_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_HOUSE_CHECKUP_BENSON;
import static seedu.address.testutil.TypicalEvents.HOUSE_CHECKUP_BENSON;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void equals() {
        // same values -> returns true
        Event meetingWithAliceCopy = new EventBuilder().build();
        System.out.println(meetingWithAliceCopy);
        assertTrue(MEETING_WITH_ALICE.equals(meetingWithAliceCopy));
        System.out.println(2);
        // same object -> returns true
        assertTrue(MEETING_WITH_ALICE.equals(MEETING_WITH_ALICE));
        System.out.println(3);
        // null -> returns false
        assertFalse(MEETING_WITH_ALICE.equals(null));
        System.out.println(4);
        // different type -> returns false
        assertFalse(MEETING_WITH_ALICE.equals(5));
        System.out.println(5);
        // different event -> returns false
        assertFalse(MEETING_WITH_ALICE.equals(HOUSE_CHECKUP_BENSON));

        // different title -> returns false
        Event updatedAlice = new EventBuilder(MEETING_WITH_ALICE)
                .withHeading(VALID_HEADING_HOUSE_CHECKUP_BENSON).build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));

        // different time -> returns false
        updatedAlice = new EventBuilder(MEETING_WITH_ALICE).withLocalDateTime(VALID_TIME_HOUSE_CHECKUP_BENSON).build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));

        // different client name -> returns false
        updatedAlice = new EventBuilder(MEETING_WITH_ALICE)
                .withClientName(VALID_CLIENT_NAME_HOUSE_CHECKUP_BENSON).build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));

        // different description -> returns false
        updatedAlice = new EventBuilder(MEETING_WITH_ALICE)
                .withDescription(VALID_DESCRIPTION_HOUSE_CHECKUP_BENSON).build();
        assertFalse(MEETING_WITH_ALICE.equals(updatedAlice));
    }
}
