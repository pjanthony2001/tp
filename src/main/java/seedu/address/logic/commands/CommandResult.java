package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final boolean isDisplayCommand;
    private Person firstMatchedPerson;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isExit,
                         boolean isDisplayCommand, Person firstMatchedPerson) {

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isDisplayCommand = isDisplayCommand;
        this.firstMatchedPerson = firstMatchedPerson;
    }
    /**
     * Constructs a CommandResult object with the specified feedback to the user and the first matched person.
     *
     * @param feedbackToUser The feedback message to be displayed to the user.
     * @param firstMatchedPerson The first matched person associated with the command result.
     * @throws NullPointerException If {@code firstMatchedPerson} is null.
     */
    public CommandResult(String feedbackToUser, Person firstMatchedPerson) {
        this(feedbackToUser, false, false, true, firstMatchedPerson);
        requireNonNull(firstMatchedPerson);
    }

    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isExit) {
        this(feedbackToUser, isShowHelp, isExit, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Person getPerson() {
        return firstMatchedPerson;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isDisplayCommand() {
        return isDisplayCommand;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", isShowHelp)
                .add("exit", isExit)
                .toString();
    }

}
