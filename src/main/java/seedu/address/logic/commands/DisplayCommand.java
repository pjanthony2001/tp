package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Represents a command to display persons whose names contain specified keywords.
 * A DisplayCommand object corresponds to a user command to display persons based on name keywords.
 */
public class DisplayCommand extends Command {

    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays person whose name contains "
            + " the specified keyword.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " alice";

    private final NameContainsKeywordsPredicate predicate;

    public DisplayCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(predicate);

        if (model.getFilteredPersonList().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Person firstMatchedPerson = model.getFilteredPersonList().get(0);
        model.updateFilteredPersonList(p -> p.equals(firstMatchedPerson));


        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size()), firstMatchedPerson);

    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DisplayCommand)) {
            return false;
        }
        DisplayCommand that = (DisplayCommand) other;
        return predicate.equals(that.predicate);
    }

    public String getCommandString() {
        return COMMAND_WORD;
    }

}
