package seedu.address.model.event;

import seedu.address.model.person.Description;
import seedu.address.model.person.Name;

/**
 * Event class to model the social workers events
 */
public class Event {
    private final Time time;
    private final Title title;
    private final Description description;
    private final Name clientName;

    /**
     * @param title The title for the meeting
     * @param time The time of the event
     * @param description The description of the event
     */
    public Event(Title title, Time time, Description description, Name clientName) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.clientName = clientName;
    }

    public Description getDescription() {
        return description;
    }

    public Title getTitle() {
        return title;
    }

    public Time getTime() {
        return time;
    }

    public Name getClientName() {
        return clientName;
    }
    public String getDescriptionString() {
        return description.value;
    }

    public String getTitleString() {
        return title.toString();
    }

    public String getTimeString() {
        return time.toString();
    }

    public String getClientNameString() {
        return clientName.toString();
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
        return title.equals(otherPerson.title)
                && clientName.equals(otherPerson.clientName)
                && description.equals(otherPerson.description)
                && time.equals(otherPerson.time);
    }
}
