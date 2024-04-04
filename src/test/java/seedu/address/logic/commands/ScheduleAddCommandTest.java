package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING_WITH_ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.history.ModelState;
import seedu.address.history.exceptions.HistoryException;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Calendar;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCalendar;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;


class ScheduleAddCommandTest {
    @Test
    void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleAddCommand(null));
    }
    @Test
    void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new ScheduleAddCommand(validEvent).execute(modelStub);

        assertEquals(String.format(ScheduleAddCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        ScheduleAddCommand scheduleAddCommand = new ScheduleAddCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class,
                ScheduleAddCommand.MESSAGE_DUPLICATE_EVENT, () -> scheduleAddCommand.execute(modelStub));
    }

    @Test
    void equals() {
        ScheduleAddCommand scheduleAddCommandAlice = new ScheduleAddCommand(MEETING_WITH_ALICE);
        ScheduleAddCommand scheduleAddCommandDifferentHeading = new ScheduleAddCommand(new EventBuilder()
                .withHeading("different")
                .build());
        // same object -> returns true
        assertTrue(scheduleAddCommandAlice.equals(scheduleAddCommandAlice));

        // same values -> returns true
        ScheduleAddCommand scheduleAddCommandAliceCopy = new ScheduleAddCommand(MEETING_WITH_ALICE);
        assertTrue(scheduleAddCommandAlice.equals(scheduleAddCommandAliceCopy));

        // different types -> returns false
        assertFalse(scheduleAddCommandAlice.equals(1));

        // null -> returns false
        assertFalse(scheduleAddCommandAlice.equals(null));

        // different event -> returns false
        assertFalse(scheduleAddCommandAlice.equals(scheduleAddCommandDifferentHeading));
    }

    @Test
    public void toStringMethod() {
        ScheduleAddCommand scheduleAddCommand = new ScheduleAddCommand(MEETING_WITH_ALICE);
        String expected = ScheduleAddCommand.class.getCanonicalName() + "{toAdd=" + MEETING_WITH_ALICE + "}";
        assertEquals(expected, scheduleAddCommand.toString());
    }

    @Test
    public void getCommandStringTest() {
        ScheduleAddCommand scheduleAddCommand = new ScheduleAddCommand(MEETING_WITH_ALICE);
        String expected = "schedule " + ScheduleAddCommand.COMMAND_WORD;
        assertEquals(expected, scheduleAddCommand.getCommandString());
    }
    /**
     * A default model stub that has all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person updatedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ModelState getCurrentState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void restoreState(ModelState modelState) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void rollBackState() throws HistoryException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void rollForwardState() throws HistoryException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateState(Command command) throws HistoryException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String retrievePreviousCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String retrieveNextCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event key) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvents(List<Event> events) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalendar(ReadOnlyCalendar calendar) {
            throw new AssertionError("This method should not be called.");
        }
    }
    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithEvent extends ScheduleAddCommandTest.ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accepts the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyCalendar getCalendar() {
            return new Calendar();
        }
    }
}
