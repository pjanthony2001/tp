package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.Heading;
import seedu.address.model.event.Time;
import seedu.address.model.person.Description;


/**
 * Typical Events to use for testing
 */
public class TypicalEvents {
    public static final Event MEETING_WITH_ALICE = new Event(
            new Heading("Meeting with Alice"),
            new Time(LocalDateTime.of(2024, Month.APRIL, 12, 10, 0)),
            new Description("Discuss Financial Matters"),
            ALICE.getName());
    public static final Event HOUSE_CHECKUP_BENSON = new Event(
            new Heading("House Checkup Benson"),
            new Time(LocalDateTime.of(2024, Month.APRIL, 13, 11, 0)),
            new Description("Discuss Medical Matters"),
            BENSON.getName());
    public static final Event FINANCIAL_ASSISTANCE_CARL = new Event(
            new Heading("Finanical Assistance"),
            new Time(LocalDateTime.of(2024, Month.APRIL, 14, 12, 0)),
            new Description("Discuss more financial assistance"),
            CARL.getName());

    // MANUALLY ADDED
    public static final Event MEDICAL_REVIEW_HOON = new Event(
            new Heading("Medical Review Hoon"),
            new Time(LocalDateTime.of(2024, Month.APRIL, 15, 19, 0)),
            new Description("Discuss outpatient plan"),
            HOON.getName());

    public static final Event UNEMPLOYMENT_TALK_IDA = new Event(
            new Heading("Unemployment Talk Ida"),
            new Time(LocalDateTime.of(2024, Month.APRIL, 19, 12, 0)),
            new Description("Discuss unemployment benefits plan"),
            IDA.getName());

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
