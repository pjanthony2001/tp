package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.matchesKeywords(person.getEmail().toString());
    }
}


