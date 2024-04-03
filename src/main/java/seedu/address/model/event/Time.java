package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * An object that represents the time for a particular event
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time should be of format: EEEE, MMMM, dd, yyyy - hh:mm a. "
            + "Note that day of the week (EEEE), month (MMMM), day of the month (dd), year (yyyy), "
            + "time in 12-hour format (hh:mm), and am/pm indicator (a).";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, MMMM, dd, yyyy - hh:mm a");
    public final String time;
    private final LocalDateTime localDateTime;

    /**
     * Constructs a Time object from a string representation of time.
     *
     * @param timeString The string representation of time.
     */
    public Time(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        localDateTime = LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
        time = DATE_TIME_FORMATTER.format(localDateTime);
    }
    /**
     * Constructs a Time object from a LocalDateTime object.
     *
     * @param timeObject The LocalDateTime object representing the time.
     */
    public Time(LocalDateTime timeObject) {
        requireNonNull(timeObject);
        localDateTime = timeObject;
        time = DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
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
