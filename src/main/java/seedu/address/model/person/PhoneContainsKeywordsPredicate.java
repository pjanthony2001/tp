package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Phone number} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.matchesKeywords(person.getPhone().value);
    }
}
