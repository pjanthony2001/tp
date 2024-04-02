package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Heading;
import seedu.address.model.event.Time;
import seedu.address.model.person.Description;
import seedu.address.model.person.Name;

public class JsonAdaptedEventTest {
    private static final String INVALID_HEADING = "Meeting with R@chel";
    private static final String INVALID_CLIENT_NAME = "R@chel";
    private static final String INVALID_TIME = "24 March";
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_HEADING = MEETING_WITH_ALICE.getHeadingString();
    private static final String VALID_CLIENT_NAME = MEETING_WITH_ALICE.getClientNameString();
    private static final String VALID_TIME = MEETING_WITH_ALICE.getTimeString();
    private static final String VALID_DESCRIPTION = MEETING_WITH_ALICE.getDescriptionString();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(MEETING_WITH_ALICE);
        assertEquals(MEETING_WITH_ALICE, event.toModelType());
    }

    @Test
    public void toModelType_invalidHeading_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(INVALID_HEADING, VALID_TIME,
                VALID_CLIENT_NAME, VALID_DESCRIPTION);
        String expectedMessage = Heading.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullHeading_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_TIME,
                VALID_CLIENT_NAME, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Heading.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        //        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_HEADING, INVALID_TIME,
        //                VALID_CLIENT_NAME, VALID_DESCRIPTION);
        //        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        //        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
        //        @Rishit modify this test to check for valid time
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_HEADING, null,
                VALID_CLIENT_NAME, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidClientName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_HEADING, VALID_TIME,
                INVALID_CLIENT_NAME, VALID_DESCRIPTION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullClientName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_HEADING, VALID_TIME,
                null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_HEADING, VALID_TIME,
                VALID_CLIENT_NAME, INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_HEADING, VALID_TIME,
                VALID_CLIENT_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
