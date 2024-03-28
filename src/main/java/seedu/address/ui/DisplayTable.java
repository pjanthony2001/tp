package seedu.address.ui;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Display Table class that is to be swapped out from the Person list.
 */
public class DisplayTable extends UiPart<Region> {
    private static final String FXML = "DisplayTable.fxml";
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
    public DisplayTable(Person person) {
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

        private static final String NAME_FIELD = "Name";
        private static final String PHONE_FIELD = "Phone";
        private static final String EMAIL_FIELD = "Email";
        private static final String ADDRESS_FIELD = "Address";
        private static final String NOK_FIELD = "Next of Kin";

        private final FieldDescription name;
        private final FieldDescription phone;
        private final FieldDescription email;
        private final FieldDescription address;
        private final FieldDescription nok;

        public DisplayPerson(Person person) {
            this.name = new FieldDescription(NAME_FIELD, person.getName().toString());
            this.email = new FieldDescription(EMAIL_FIELD, person.getEmail().toString());
            this.phone = new FieldDescription(PHONE_FIELD, person.getPhone().toString());
            this.address = new FieldDescription(ADDRESS_FIELD, person.getAddress().toString());
            this.nok = new FieldDescription(NOK_FIELD, person.getNextOfKin().toString());
        }

        private ObservableList<FieldDescription> getFieldDescriptions() {
            return FXCollections.observableArrayList(name, email, phone, address, nok);
        }

        public static class FieldDescription {
            private final SimpleStringProperty fieldProperty;
            private final SimpleStringProperty descriptionProperty;

            public FieldDescription(String field, String description) {
                fieldProperty = new SimpleStringProperty(field);
                descriptionProperty = new SimpleStringProperty(description);
            }
            public SimpleStringProperty fieldProperty() {
                return fieldProperty;
            }
            public SimpleStringProperty descriptionProperty() {
                return descriptionProperty;
            }
            public String getFieldProperty() {
                return fieldProperty.get();
            }
            public void setDescriptionProperty(String fName) {
                descriptionProperty.set(fName);
            }
            public String getDescriptionProperty() {
                return descriptionProperty.get();
            }
        }
    }
}

