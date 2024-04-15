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
    public static final String MESSAGE_CONSTRAINTS = "Time should be of format: M/d/yyyy HHmm. \n"
            + "Note that: month (M), day (d), year(yyyy), time in 24 hour format (HHmm)\n"
            + "Example: 2/14/2024 0930";

    public final String time;
    private final LocalDateTime localDateTime;

    /**
     * Constructs a Time object from a string representation of the time.
     *
     * @param timeString The string representation of time.
     */
    public Time(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        localDateTime = parseDate(timeString);
        time = stringifyDate(localDateTime);
    }
    /**
     * Constructs a Time object from a LocalDateTime object.
     *
     * @param timeObject The LocalDateTime object representing the time.
     */
    public Time(LocalDateTime timeObject) {
        requireNonNull(timeObject);
        localDateTime = timeObject;
        time = stringifyDate(localDateTime);
    }
    /**
     * Parses the given date string into a LocalDateTime object.
     *
     * @param date The date string to parse.
     * @return A LocalDateTime object parsed from the input date string.
     * @throws DateTimeParseException If the input string cannot be parsed into a valid LocalDateTime.
     */
    private static LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        return LocalDateTime.parse(date, formatter);
    }
    /**
     * Converts the given LocalDateTime object into a string representation in a specific format.
     *
     * @param date The LocalDateTime object to convert.
     * @return A string representation of the input LocalDateTime in the specified format.
     */
    public static String stringifyDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
    }
    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            parseDate(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    @Override
    public String toString() {
        return stringifyDate(localDateTime);
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
