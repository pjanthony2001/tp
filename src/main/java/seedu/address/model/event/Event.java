package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Description;
import seedu.address.model.person.Name;

/**
 * Event class to model the social workers events
 */
public class Event {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, MMMM, dd, yyyy - hh:mm a");
    private final LocalDateTime time;
    private final Title title;
    private final Description description;
    private final Name clientName;

    /**
     * @param title The title for the meeting
     * @param time The time of the event
     * @param description The description of the event
     */
    public Event(Title title, LocalDateTime time, Description description, Name clientName) {
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

    public LocalDateTime getLocalDateTime() {
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
        return time.format(DATE_TIME_FORMATTER);
        // Friday, Mar 29, 2024 - 05:58 PM;
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

    public static void main(String[] args) {
        Event event = new Event(null, LocalDateTime.now(), null, null);
        String parsedTime = event.time.format(DATE_TIME_FORMATTER);
        System.out.println(parsedTime);
        LocalDateTime.parse(parsedTime, DATE_TIME_FORMATTER);
    }
}
