package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An object that represents the time for a particular event
 */
public class Time {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, MMMM, dd, yyyy - hh:mm a");
    private LocalDateTime localDateTime;
    public Time(String timeString) {
        localDateTime = LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
    }
    public Time(LocalDateTime timeObject) {
        localDateTime = timeObject;
    }

    //@Rishit need to add isValidTime method

    @Override
    public String toString() {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Time)) {
            return false;
        }

        Time otherPerson = (Time) other;
        return localDateTime.equals(otherPerson.localDateTime);
    }
}
