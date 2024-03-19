package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;


public class DisplayCommand extends Command{

    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays person whose name contains "
            + " the specified keyword.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " alice";

    private final NameContainsKeywordsPredicate predicate;

    public DisplayCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));

    }

}
