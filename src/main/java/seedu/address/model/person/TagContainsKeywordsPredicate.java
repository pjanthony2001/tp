package seedu.address.model.person;

import java.util.List;


/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate extends KeywordMatcherPredicate {

    public TagContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        if (person.getTags().isEmpty()) {
            return false;
        }
        return super.matchesKeywords(person.getTags().stream()
                .map(tag -> tag.tagName)
                .reduce("", (x, y) -> x + " " + y));
    }


}
