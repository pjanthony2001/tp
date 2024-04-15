package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Description} matches any of the
 * keywords given.
 */
public class DescriptionContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        if (person.getDescription().isPresent()) {
            return false;
        }
        return super.matchesKeywords(person.getDescription().get().toString());
    }

}
