package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;

/**
 * Unmodifiable view of a calendar
 */
public interface ReadOnlyCalendar {
    /**
     * Returns an unmodifiable view of the event list.
     * This list will not contain any duplicate events?.
     */
    ObservableList<Event> getEventList();
    ReadOnlyCalendar deepCopy() throws IllegalValueException;
}
