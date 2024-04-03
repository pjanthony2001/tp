package seedu.address.model.event;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Description;
import seedu.address.model.person.Name;


/**
 * Represents an event related to social work, such as meetings, appointments, or tasks.
 */
public class Event {
    private final Time time;
    private final Heading heading;
    private final Description description;
    private final Name clientName;

    /**
     * Constructs an event with the specified heading, time, description, and client name.
     *
     * @param heading     The heading for the event.
     * @param time        The time of the event.
     * @param description The description of the event.
     * @param clientName  The name of the client associated with the event.
     */
    public Event(Heading heading, Time time, Description description, Name clientName) {
        this.heading = heading;
        this.time = time;
        this.description = description;
        this.clientName = clientName;
    }

    /**
     * Returns the description of the event.
     *
     * @return The description of the event as a {@code Description} object.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns the heading of the event.
     *
     * @return The heading of the event as a {@code Heading} object.
     */
    public Heading getHeading() {
        return heading;
    }

    /**
     * Returns the time of the event.
     *
     * @return The time of the event as a {@code Time} object.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Returns the name of the client associated with the event.
     *
     * @return The name of the client as a {@code Name} object.
     */
    public Name getClientName() {
        return clientName;
    }

    /**
     * Returns the string representation of the event description.
     *
     * @return The string representation of the event description.
     */
    public String getDescriptionString() {
        return description.value;
    }

    /**
     * Returns the string representation of the event heading.
     *
     * @return The string representation of the event heading.
     */
    public String getHeadingString() {
        return heading.toString();
    }

    /**
     * Returns the string representation of the event time.
     *
     * @return The string representation of the event time.
     */
    public String getTimeString() {
        return time.toString();
    }

    /**
     * Returns the string representation of the client name associated with the event.
     *
     * @return The string representation of the client name.
     */
    public String getClientNameString() {
        return clientName.toString();
    }

    /**
     * Returns true if both events have the same heading.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }
        return otherEvent != null
                && otherEvent.getHeading().equals(getHeading());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherPerson = (Event) other;
        return heading.equals(otherPerson.heading)
                && clientName.equals(otherPerson.clientName)
                && description.equals(otherPerson.description)
                && time.equals(otherPerson.time);
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(clientName, heading, description, time);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", clientName)
                .add("heading", heading)
                .add("description", description)
                .add("time", time)
                .toString();
    }
}
