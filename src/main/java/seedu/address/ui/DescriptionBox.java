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

public class DescriptionBox extends UiPart<Region> {
    private static final String FXML = "DescriptionBox.fxml";
    @FXML
    private TableColumn<DescriptionBox.DisplayPerson.FieldDescription, String> description;

    @FXML
    private TableView<DescriptionBox.DisplayPerson.FieldDescription> table;
    private final ObservableList<DescriptionBox.DisplayPerson.FieldDescription> data;
    private final DescriptionBox.DisplayPerson displayPerson;

    /**
     * Display table for the display command
     */
    public DescriptionBox(Person person) {
        //Constructor should take in a person class in the future
        super(FXML);
        displayPerson = new DescriptionBox.DisplayPerson(person);
        data = displayPerson.getFieldDescriptions();

        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setEditable(true);
        table.setItems(data);
    }
    private static class DisplayPerson {
        private static final String DESCRIPTION = "Description";

        private final DescriptionBox.DisplayPerson.FieldDescription desc;

        public DisplayPerson(Person person) {

            this.desc = new DescriptionBox.FieldDescription(DESCRIPTION, person.getDescription().toString());
        }

        private ObservableList<DescriptionBox.DisplayPerson.FieldDescription> getFieldDescriptions() {
            return FXCollections.observableArrayList(desc);
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



