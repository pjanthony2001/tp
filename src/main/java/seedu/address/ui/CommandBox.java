package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final NextCommandRetriever nextCommandRetriever;
    private final PreviousCommandRetriever previousCommandRetriever;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, NextCommandRetriever nextCommandRetriever,
                      PreviousCommandRetriever previousCommandRetriever) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.nextCommandRetriever = nextCommandRetriever;
        this.previousCommandRetriever = previousCommandRetriever;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.setOnKeyPressed(this::handleKeyPress);
    }

    /**
     * Handles Key Press event
     */
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            System.out.println("Up key pressed");
            commandTextField.setText(previousCommandRetriever.retrievePrevious());
            break;
        case DOWN:
            System.out.println("Down key pressed");
            commandTextField.setText(nextCommandRetriever.retrieveNext());
            break;
        case TAB:
            System.out.println("Tab key pressed");
            commandTextField.setText("TAB KEY PRESSED");
            break;
        default:
            break;
        }
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents a function that can return the previously entered command in history to be displayed
     */
    @FunctionalInterface
    public interface PreviousCommandRetriever {
        /**
         * Retrieves the previous command. Should throw History Exception
         *
         */
        String retrievePrevious();
    }

    /**
     * Represents a function that can return the "next" entered command in history to be displayed
     */
    @FunctionalInterface
    public interface NextCommandRetriever {
        /**
         * Retrieves the previous command. Should throw History Exception
         *
         */
        String retrieveNext();
    }
}
