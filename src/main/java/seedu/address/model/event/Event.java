package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Description;
import seedu.address.model.person.Person;

/**
 * Event class to model the social workers events
 */
public class Event {
    private final LocalDateTime time;
    private final Title title;
    private final Description description;
    private final Person client;

    /**
     * @param title The title for the meeting
     * @param time The time of the event
     * @param description The description of the event
     */
    public Event(Title title, LocalDateTime time, Description description, Person client) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.client = client;
    }

    public String getDescriptionString() {
        return description.value;
    }

    public String getTitleString() {
        return title.toString();
    }

    public String getTimeString() {
        return time.format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy - hh:mm a"));
        // Friday, Mar 29, 2024 - 05:58 PM;
    }

    public String getClientNameString() {
        return client.getName().toString();
    }
}
