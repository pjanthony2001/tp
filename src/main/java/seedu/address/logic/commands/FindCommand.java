package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.KeywordMatcherPredicate;
import seedu.address.model.person.Person;
/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords.
 * Keyword matching is case insensitive.
 * Optionally can use parameters to search for specific fields as well.
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case in-sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and optionally by additional parameters and \n"
            + "displays them as a list with index numbers.\n"
            + "Parameters: [OPTIONAL PARAMETER] [OPTIONAL PARAMETER] [OPTIONAL KEYWORD]...\n"
            + "A minimum of ONE parameter must be passed in. \n"
            + "Example: " + COMMAND_WORD + " n/alice n/bob p/1 e/nus a/clementi t/friends k/amy d/colleague\n";

    private final List<Predicate<Person>> predicates;

    /**
     * Constructor for FindCommand
     */
    public FindCommand(KeywordMatcherPredicate... predicates) {
        super.setReversible(true);
        for (KeywordMatcherPredicate predicate : predicates) {
            requireNonNull(predicate);
        }
        this.predicates = Arrays.asList(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Optional<Predicate<Person>> combinedPredicate = predicates.stream().reduce(Predicate::or);
        combinedPredicate.ifPresent(model::updateFilteredPersonList);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicates.equals(otherFindCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicates", predicates)
                .toString();
    }

    @Override
    public String getCommandString() {
        return COMMAND_WORD;
    }
}
