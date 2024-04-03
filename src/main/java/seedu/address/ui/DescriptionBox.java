package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;



/**
 * Display Table class that is to be swapped out from the Person list.
 */
public class DescriptionBox extends UiPart<Region> {
    private static final String FXML = "DescriptionBox.fxml";
    @FXML
    private TableColumn<DisplayPerson.FieldDescription, String> field;
    @FXML
    private TableColumn<DisplayPerson.FieldDescription, String> description;
    @FXML
    private TableView<DisplayPerson.FieldDescription> table;
    private final ObservableList<DisplayPerson.FieldDescription> data;
    private final DisplayPerson displayPerson;

    /**
     * Display table for the display command
     */
    public DescriptionBox(Person person) {
        //Constructor should take in a person class in the future
        super(FXML);
        displayPerson = new DisplayPerson(person);
        data = displayPerson.getFieldDescriptions();

        field.setCellValueFactory(new PropertyValueFactory<>("field"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setEditable(true);
        table.setItems(data);
    }
    private static class DisplayPerson {
        // Change method to utilise optionals instead of directly assuming the fields exist in the Person
        // person.getField().map(person -> String).orElse(DEFAULT)

        private static final String DESCRIPTION_FIELD = "Description";

        private final FieldDescription description;

        public DisplayPerson(Person person) {
            this.description = new FieldDescription(DESCRIPTION_FIELD, person.getDescription().toString());
        }

        private ObservableList<FieldDescription> getFieldDescriptions() {
            return FXCollections.observableArrayList(description);
        }

        public static class FieldDescription {
            private final SimpleStringProperty fieldProperty;
            private final SimpleStringProperty descriptionProperty;

            public FieldDescription(String field, String description) {
                fieldProperty = new SimpleStringProperty(field);
                descriptionProperty = new SimpleStringProperty(description);
            }

        }
    }
}

