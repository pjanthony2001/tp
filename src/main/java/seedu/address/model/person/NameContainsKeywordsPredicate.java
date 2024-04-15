package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Person person) {
        return super.matchesKeywords(person.getName().fullName);
    }

}
