package seedu.address.ui;

import java.lang.reflect.Field;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

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
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DisplayCard(DisplayListPanel.FieldDescription fieldDescription) {
        super(FXML);
        this.fieldDescription = fieldDescription;
        field.setText(fieldDescription.getField());
        description.setText(fieldDescription.getDescription());

    }
}
