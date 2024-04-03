package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.HOUSE_CHECKUP_BENSON;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(MEETING_WITH_ALICE));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(MEETING_WITH_ALICE);
        assertTrue(uniqueEventList.contains(MEETING_WITH_ALICE));
    }
    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.add(MEETING_WITH_ALICE);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(MEETING_WITH_ALICE));
    }
    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, MEETING_WITH_ALICE));
    }

    @Test
    public void setEvent_nullUpdatedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(MEETING_WITH_ALICE, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList
                .setEvent(MEETING_WITH_ALICE, MEETING_WITH_ALICE));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(MEETING_WITH_ALICE));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(MEETING_WITH_ALICE);
        uniqueEventList.remove(MEETING_WITH_ALICE);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(MEETING_WITH_ALICE);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(HOUSE_CHECKUP_BENSON);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(MEETING_WITH_ALICE);
        List<Event> personList = Collections.singletonList(HOUSE_CHECKUP_BENSON);
        uniqueEventList.setEvents(personList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(HOUSE_CHECKUP_BENSON);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(MEETING_WITH_ALICE, MEETING_WITH_ALICE);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }
    @Test
    public void setEvent_validTargetAndUpdatedEvent_success() {
        uniqueEventList.add(HOUSE_CHECKUP_BENSON);
        uniqueEventList.setEvent(HOUSE_CHECKUP_BENSON, MEETING_WITH_ALICE);
        assertEquals(MEETING_WITH_ALICE, uniqueEventList.asUnmodifiableObservableList().get(0));
    }
    @Test
    public void setEvent_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, HOUSE_CHECKUP_BENSON));
    }
    @Test
    public void setEvent_targetNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList
                .setEvent(MEETING_WITH_ALICE, HOUSE_CHECKUP_BENSON));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueEventList.asUnmodifiableObservableList().toString(), uniqueEventList.toString());
    }

    @Test
    public void equals() {
        assertTrue(uniqueEventList.equals(uniqueEventList)); // same instance
        assertFalse(uniqueEventList.equals(null)); // null
        assertFalse(uniqueEventList.equals(0213)); // not same type
    }
}
