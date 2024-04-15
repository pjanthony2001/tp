package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the heading of an Event object
 */
public class Heading {

    public static final String MESSAGE_CONSTRAINTS =
            "Heading should only contain alphanumeric characters and spaces, cannot be more than 70 characters long "
                    + "and it should not be blank";

    /*
     * The first character of the heading must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String heading;

    /**
     * Constructs a {@code Heading}.
     *
     * @param heading A valid heading for the event.
     */
    public Heading(String heading) {
        requireNonNull(heading);
        checkArgument(isValidHeading(heading), MESSAGE_CONSTRAINTS);
        this.heading = heading;
    }

    /**
     * Returns true if a given string is a valid heading.
     */
    public static boolean isValidHeading(String test) {
        return (test.length() <= 70) && (test.matches(VALIDATION_REGEX));
    }

    @Override
    public String toString() {
        return heading;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Heading)) {
            return false;
        }

        Heading otherName = (Heading) other;
        return heading.equals(otherName.heading);
    }

    @Override
    public int hashCode() {
        return heading.hashCode();
    }
}
