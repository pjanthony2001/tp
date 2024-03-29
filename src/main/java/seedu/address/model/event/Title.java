package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the title of an Event object
 */
public class Title {

    public static final String MESSAGE_CONSTRAINTS =
            "Title should only contain alphanumeric characters and spaces, cannot be more than 70 characters long "
                    + "and it should not be blank";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String title;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A valid name.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTitle(String test) {
        return (test.length() < 70) && (test.matches(VALIDATION_REGEX));
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.event.Title)) {
            return false;
        }

        seedu.address.model.event.Title otherName = (seedu.address.model.event.Title) other;
        return title.equals(otherName.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
