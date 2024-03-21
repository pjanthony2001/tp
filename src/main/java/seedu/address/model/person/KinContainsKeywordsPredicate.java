package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Next of Kin} matches any of the keywords given.
 */
public class KinContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public KinContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return true;
        }
        boolean personmatches = keywords.stream()
                .anyMatch(keyword -> person.getNextOfKin().toString().contains(keyword));
        return personmatches;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof KinContainsKeywordsPredicate)) {
            return false;
        }

        KinContainsKeywordsPredicate otherKinContainsKeywordsPredicate = (KinContainsKeywordsPredicate) other;
        return keywords.equals(otherKinContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

