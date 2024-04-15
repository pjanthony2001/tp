package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Next of Kin} matches any of the keywords given.
 */
public class KinContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public KinContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.matchesKeywords(person.getNextOfKin().get().toString());
    }
}

