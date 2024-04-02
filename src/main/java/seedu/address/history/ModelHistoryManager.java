package seedu.address.history;

/**
 * The `ModelHistoryManager` class manages the history of commands used in the ConnectCare application.
 * It facilitates the undoing and redoing commands and updating the command history.
 */
public class ModelHistoryManager extends HistoryManager<ModelState> {
    /**
     * Constructs a new ModelHistoryManager with a starting state.
     *
     * @param startModelState The initial state of the history.
     */
    public ModelHistoryManager(ModelState startModelState) {
        super(startModelState);
    }
}
