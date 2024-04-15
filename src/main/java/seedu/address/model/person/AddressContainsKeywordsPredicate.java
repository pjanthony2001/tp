package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        if (person.getAddress().isPresent()) {
            return false;
        }
        return super.matchesKeywords(person.getAddress().get().toString());
    }
}
