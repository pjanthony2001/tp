package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.Title;
import seedu.address.model.person.Description;




/**
 * Typical Events to use for testing
 */
public class TypicalEvents {
    public static final Event MEETING_WITH_ALICE = new Event(
            new Title("Client Meeting"),
            LocalDateTime.now(),
            new Description("DUMMY DESCRIPTION"),
            ALICE.getName());
    public static final Event HOUSE_CHECKUP_BENSON = new Event(
            new Title("House Checkup"),
            LocalDateTime.now(),
            new Description("DUMMY DESCRIPTION"),
            BENSON.getName());
    public static final Event FINANCIAL_ASSISTANCE_CARL = new Event(
            new Title("Finanical Assistance"),
            LocalDateTime.now(),
            new Description("DUMMY DESCRIPTION"),
            CARL.getName());

    public static Calendar getTypicalCalendar() {
        Calendar calendar = new Calendar();
        for (Event event : getTypicalEvents()) {
            calendar.addEvent(event);
        }
        return calendar;
    }
    public static ArrayList<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(
                MEETING_WITH_ALICE,
                HOUSE_CHECKUP_BENSON,
                FINANCIAL_ASSISTANCE_CARL));
    }
}
