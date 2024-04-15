package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.storage.JsonSerializableCalendar;

/**
 * Wraps all data at the calendar level
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class Calendar implements ReadOnlyCalendar {
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        events = new UniqueEventList();
    }

    public Calendar() {}

    /**
     * Creates a Calendar using the Events in the {@code toBeCopied}
     */
    public Calendar(ReadOnlyCalendar toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the eventss list with {@code persons}.
     * {@code events} must not contain duplicate persons.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code updatedEvent}.
     * {@code target} must exist in the calendar.
     * The calendar identity of {@code updatedEvent} must not be the same as another existing event in the calendar.
     */
    public void setEvent(Event target, Event updatedEvent) {
        requireNonNull(updatedEvent);

        events.setEvent(target, updatedEvent);
    }
    /**
     * Resets the existing data of this {@code ReadOnlyCalendar} with {@code newData}.
     */
    public void resetData(ReadOnlyCalendar newData) {
        requireNonNull(newData);

        setEvents(newData.getEventList());
    }

    //// event-level operations

    /**
     * Returns true if an event with the same "identity" (heading) as {@code event} exists in the calendar.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the calendar.
     * The event must not already exist in the calendar.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes {@code key} from this {@code Calendar}.
     * {@code key} must exist in the calendar.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("events", events)
                .toString();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ReadOnlyCalendar deepCopy() throws IllegalValueException {
        return new JsonSerializableCalendar(this).toModelType();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Calendar)) {
            return false;
        }

        Calendar otherCalendar = (Calendar) other;
        return events.equals(otherCalendar.events);
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
}
