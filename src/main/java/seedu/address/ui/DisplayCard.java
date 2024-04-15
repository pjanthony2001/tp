package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class DisplayCard extends UiPart<Region> {

    private static final String FXML = "DisplayListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final DisplayListPanel.FieldDescription fieldDescription;

    @FXML
    private HBox cardPane;
    @FXML
    private Label field;
    @FXML
    private Label description;


    /**
     * Constructs a DisplayCard for displaying details of a specified field from a person's record.
     * This card extracts the field name and description from {@code fieldDescription} and assigns them
     * to the labels in the UI.
     */
    public DisplayCard(DisplayListPanel.FieldDescription fieldDescription) {
        super(FXML);
        this.fieldDescription = fieldDescription;
        field.setText(fieldDescription.getField());
        description.setText(fieldDescription.getDescription());

    }
}
