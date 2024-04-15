package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Superclass for keyword matching predicates.
 */
public abstract class KeywordMatcherPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public KeywordMatcherPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

    /**
     * Tests if the given text contains any of the keywords.
     * @param text text to be checked
     * @return true if text found, false otherwise
     */
    public boolean matchesKeywords(String text) {
        if (keywords.isEmpty()) {
            return false;
        }
        boolean personmatches = keywords.stream()
                .anyMatch(
                    keyword -> StringUtil.containsWordIgnoreCase(text, keyword));
        return personmatches;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof KeywordMatcherPredicate)) {
            return false;
        }

        KeywordMatcherPredicate otherKeywordMatcherPredicate = (KeywordMatcherPredicate) other;
        return keywords.equals(otherKeywordMatcherPredicate.keywords);
    }

}
