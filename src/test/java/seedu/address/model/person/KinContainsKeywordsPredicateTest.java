package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class KinContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        KinContainsKeywordsPredicate firstPredicate = new KinContainsKeywordsPredicate(firstPredicateKeywordList);
        KinContainsKeywordsPredicate secondPredicate = new KinContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        KinContainsKeywordsPredicate firstPredicateCopy = new KinContainsKeywordsPredicate(
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
    public void test_kinContainsKeywords_returnsTrue() {
        // One keyword
        KinContainsKeywordsPredicate predicate = new KinContainsKeywordsPredicate(
            Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withNextOfKin("Alice").build()));

        // keyword not at the start of Kin
        predicate = new KinContainsKeywordsPredicate(Arrays.asList("Bob"));
        assertFalse(predicate.test(new PersonBuilder().withNextOfKin("AliceBob").build()));

        // Only one matching keyword
        predicate = new KinContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withNextOfKin("Carol").build()));

        // Mixed-case keywords
        predicate = new KinContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withNextOfKin("Alice BOB").build()));
    }

    @Test
    public void test_kinDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        KinContainsKeywordsPredicate predicate = new KinContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withNextOfKin("Alice").build()));

        // Non-matching keyword
        predicate = new KinContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withNextOfKin("Alice").build()));

    }

}

