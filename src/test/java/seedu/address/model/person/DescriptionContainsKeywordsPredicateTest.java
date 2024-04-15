package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate = new DescriptionContainsKeywordsPredicate(
            firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate = new DescriptionContainsKeywordsPredicate(
            secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy = new DescriptionContainsKeywordsPredicate(
            firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
            Collections.singletonList("ill"));
        assertTrue(predicate.test(new PersonBuilder().withDescription("ill").build()));

        // keyword not at the start of Description
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("well"));
        assertFalse(predicate.test(new PersonBuilder().withDescription("unwell").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("unwell", "ill"));
        assertTrue(predicate.test(new PersonBuilder().withDescription("unwell").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("uNwELL", "iLl"));
        assertTrue(predicate.test(new PersonBuilder().withDescription("Unwell").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
            Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDescription("unwell").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("ill"));
        assertFalse(predicate.test(new PersonBuilder().withDescription("unwell").build()));

    }

    @Test
    public void test_personDescriptionIsEmpty_returnsFalse() {
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
            Collections.singletonList("ill"));
        assertFalse(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DescriptionContainsKeywordsPredicate predicate =
            new DescriptionContainsKeywordsPredicate(keywords);

        String expected = DescriptionContainsKeywordsPredicate.class.getCanonicalName()
            + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }

}

