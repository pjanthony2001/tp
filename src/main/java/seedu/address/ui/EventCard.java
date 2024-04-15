package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;

/**
 * A UI component that displays information of a {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label heading;
    @FXML
    private Label client;
    @FXML
    private Label time;
    @FXML
    private Label description;

    /**
     * Creates a {@code EventCard} with the given {@code Event}.
     */
    public EventCard(Event event) {
        super(FXML);
        this.event = event;
        heading.setText(event.getHeadingString());
        time.setText(event.getTimeString());
        description.setText(event.getDescriptionString());
        client.setText(event.getClientNameString());
    }
}
