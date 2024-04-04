package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;


/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private DisplayListPanel displayListPanel;
    private EventListPanel eventListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private Person displayPerson;
    private CommandBox commandBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane eventListPanelPlaceholder;

    @FXML
    private StackPane displayListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand, this::retrieveNextCommand, this::retrievePreviousCommand, (
        ) -> resultDisplay.setFeedbackToUser("Invalid command in display mode"));

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }
    /**
     * Handles the display command result by updating the UI to show the list of persons or a single person.
     * If the command result indicates a single person display,
     * the UI is updated to display details of the specified person.
     *
     * @param commandResult The command result containing the feedback message and possibly the first matched person.
     */
    public void handleDisplay(CommandResult commandResult) {
        commandBox.setIsDisplay(true);
        personListPanelPlaceholder.getChildren().clear();
        displayPerson = commandResult.getPerson();
        displayListPanel = new DisplayListPanel(displayPerson, this::executeCommand, this::swapPanelList);
        personListPanelPlaceholder.getChildren().add(displayListPanel.getRoot());
    }


    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            swapPanelList();
            if (commandResult.isDisplayCommand()) {
                handleDisplay(commandResult);
            }
            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
    private String retrieveNextCommand() throws HistoryException {
        try {
            String nextCommand = logic.retrieveNextCommand();
            logger.info("ShortCut DownArrow: " + nextCommand);
            resultDisplay.setFeedbackToUser("");
            return nextCommand;
        } catch (HistoryException e) {
            logger.info("An error occurred while executing shortcut: Up");
            resultDisplay.setFeedbackToUser("You can't roll forward the state anymore!");
            throw e;
        }
        // Should catch HistoryException, log and throw
        // After catching HistoryException, should update UI element to indicate that there are no more commands
        // to revert to.
    }

    private String retrievePreviousCommand() throws HistoryException { //Should throw HistoryException
        try {
            String previousCommand = logic.retrievePreviousCommand();
            logger.info("ShortCut UpArrow: " + previousCommand);
            resultDisplay.setFeedbackToUser("");
            return previousCommand;
        } catch (HistoryException e) {
            logger.info("An error occurred while executing shortcut: Down");
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
        // Should catch HistoryException, log and throw
        // After catching HistoryException, should update UI element to indicate that there are no more commands
        // to revert to.
    }
    /**
     * Swaps the display panel back to the list of persons panel.
     * Clears the current display panel and adds back the person list panel.
     * Sets the commandBox's display flag to false to handle subsequent command input for listing persons.
     */
    public void swapPanelList() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        commandBox.setIsDisplay(false);
    }

}
