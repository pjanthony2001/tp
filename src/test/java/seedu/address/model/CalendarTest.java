package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.testutil.EventBuilder;

public class CalendarTest {

    private final Calendar calendar = new Calendar();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), calendar.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCalendar_replacesData() {
        Calendar newData = getTypicalCalendar();
        calendar.resetData(newData);
        assertEquals(newData, calendar);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same fields
        Event otherAlice = new EventBuilder(MEETING_WITH_ALICE).build();
        List<Event> newEvents = Arrays.asList(MEETING_WITH_ALICE, otherAlice);
        CalendarStub newData = new CalendarStub(newEvents);

        assertThrows(DuplicateEventException.class, () -> calendar.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInCalendar_returnsFalse() {
        assertFalse(calendar.hasEvent(MEETING_WITH_ALICE));
    }

    @Test
    public void hasEvent_eventInCalendar_returnsTrue() {
        calendar.addEvent(MEETING_WITH_ALICE);
        assertTrue(calendar.hasEvent(MEETING_WITH_ALICE));
    }

    @Test
    public void hasEvent_eventWithSameFieldsInCalendar_returnsTrue() {
        calendar.addEvent(MEETING_WITH_ALICE);
        Event otherAlice = new EventBuilder(MEETING_WITH_ALICE).build();
        assertTrue(calendar.hasEvent(otherAlice));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> calendar.getEventList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Calendar.class.getCanonicalName() + "{events=" + calendar.getEventList() + "}";
        assertEquals(expected, calendar.toString());
    }

    /**
     * A stub ReadOnlyCalendar whose events list can violate interface constraints.
     */
    private static class CalendarStub implements ReadOnlyCalendar {
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        CalendarStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }
}
