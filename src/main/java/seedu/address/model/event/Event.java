package seedu.address.model.event;

/**
 * Event class to model the social workers events
 */
public class Event {
    private String data;
    public Event(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
}
