package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.Month;

import seedu.address.model.event.Event;
import seedu.address.model.event.Heading;
import seedu.address.model.event.Time;
import seedu.address.model.person.Description;
import seedu.address.model.person.Name;


/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_HEADING = "Meeting with Alice";
    public static final String DEFAULT_TIME = Time.stringifyDate(LocalDateTime
            .of(2024, Month.APRIL, 12, 10, 0));
    public static final String DEFAULT_DESCRIPTION = "Discuss Financial Matters";
    public static final String DEFAULT_NAME = "Alice Pauline";

    private Heading heading;
    private Time time;
    private Description description;
    private Name clientName;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        heading = new Heading(DEFAULT_HEADING);
        time = new Time(DEFAULT_TIME);
        description = new Description(DEFAULT_DESCRIPTION);
        clientName = new Name(DEFAULT_NAME);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        heading = eventToCopy.getHeading();
        time = eventToCopy.getTime();
        description = eventToCopy.getDescription();
        clientName = eventToCopy.getClientName();
    }

    /**
     * Sets the {@code Heading} of the {@code Event} that we are building.
     */
    public EventBuilder withHeading(String heading) {
        this.heading = new Heading(heading);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withClientName(String clientName) {
        this.clientName = new Name(clientName);
        return this;
    }

    /**
     * Sets the {@code LocalDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withLocalDateTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Event build() {
        return new Event(heading, time, description, clientName);
    }

}
