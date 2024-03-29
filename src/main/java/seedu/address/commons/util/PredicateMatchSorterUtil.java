package seedu.address.commons.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility class for sorting elements based on the number of predicates they match.
 */
public class PredicateMatchSorterUtil {

    /**
     * Sorts the elements based on the number of predicates they match.
     * @param <T>
     * @param elements
     * @param predicates
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> predicatematchSort(List<T> elements, List<Predicate<T>> predicates) {
        return elements.stream()
        .sorted(Comparator.comparingLong(
            element -> predicates.stream().filter(predicate -> predicate.test((T) element)).count()
        ).reversed())
        .collect(Collectors.toList());
    }
}
