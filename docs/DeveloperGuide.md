---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# ConnectCare Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This application is based off of the AddressBook Level-3 by SE-EDU.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.
* [**`History`**](#storage-component): Holds states of the model.
* [**`State`**](#storage-component): Has data on the state of the model at a specific time point.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S2-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### History component

**API** : [`History.java`](https://github.com/AY2324S2-CS2103T-W12-4/tp/blob/master/src/main/java/seedu/address/history/History.java)

<puml src="diagrams/HistoryClassDiagram.puml" width="350" />

The `History` component,
* Stores the states of the model or commands entered at different time points.
* depends on some classes in the `Model` component (because the `History` component's job is to save/retrieve objects that belong to the `Model`)

### State component

**API** : [`ModelState.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/history/ModelState.java), [`CommandState.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/history/CommandState.java) 

<puml src="diagrams/ModelStateClassDiagram.puml" width="550" />
<puml src="diagrams/CommandStateClassDiagram.puml" width="150" />

The `ModelState` component,
* Stores a single state of the model at a specific time point.
* depends on some classes in the `Model` component (because the `State` component's job is to save/retrieve objects that belong to the `Model`)
 
The `CommandState` component,
* Stores a single state of the command entered at a specific time point.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Undo/redo feature

The undo/redo mechanism is facilitated by `HistoryManager`. This class implements certain methods:

* `HistoryManager#rollBackState()` - Rolls back to the previous state in the history.
* `HistoryManager#rollForwardState()` - Rolls forward to the next state in the history.
* `HistoryManager#addState()` - Adds a new T state to the history, removing subsequent states.
* `HistoryManager#getCurrState()` - Gets the current state from the history.

These operations are exposed in the `History` interface as `History#rollBackState()`, `History#rollForwardState()`, `History#addState()` and `History#getCurrState()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `HistoryManager<ModelState>` will be initialized with the initial `modelState`, and the `currentStateIdx` is set to 0, indicating that it is at the very first index.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. After the `delete` command is executed, the method `Model#updateModelState()` is called, causing the modified model state of the application after the `delete 5` command to be added to `states` list in `HistoryManager`, by an invocation of the `History#addState()` method. The `currentStateIdx` is shifted to 1.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#updateModelState()`, causing another modified application model state to be saved into the `states` list.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#updateModelState()`, so the model state will not be saved into the `states` list.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#rollBackState()`, in turn calling `History#rollBackState`, which will decrement the `currentStateIdx` once, retrieving and restoring the address book to the model state at that index.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStateIdx` is at index 0, pointing to the initial AddressBook modelState, then there are no previous AddressBook states to restore. The call to `Model#rollBackState()` will throw a checked exception, which will be caught and return an error to the user rather than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#rollForwardState()`, which increments the `currentStateIdx`, which is the index of the previously undone model state, and restores the application to that state.

<box type="info" seamless>

**Note:** If the `currentStateIdx` is at index `addressBookStateList.size() - 1`, the index of the last state, then there are no undone states to restore. The call to `Model#rollForwardState()` will throw a checked exception, which will be caught and return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `help`. Commands that do not modify the application, such as `help`, are not "reversible" and thus, executing them do not call `History#addState()`. This means that the `states` list and the `currentStateIdx` remain unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#updateModelState` and subsequently, `History#addState`. Since the `currentStateIdx` is not at the end of the `states`, all model states after the `currentStateIdx` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* Saves the entire address book, calendar, and predicate.
  * Pros: Easy to implement, and captures all aspects of the application in the state
  * Cons: May have performance issues in terms of memory usage.

* Saves only the changes made when a command is executed.
  * Pros: Utilises a lot less space and is thus makes the application more performative
  * Cons: Much more difficult to implement and is less scalable as more commands and features are added

**Aspect: Which commands are reversible:**

* Currently, the following commands are reversible:
  * AddCommand
  * ClearCommand
  * DeleteCommand
  * DisplayCommand
  * FindCommand
  * ListCommand
  * ScheduleAddCommand
  * ScheduleDeleteCommand
  * UpdateCommand

### Display feature

The display mechanism in the application is constructed using several components, including `DisplayCommand`,
`DisplayCommandParser`, `DisplayListPanel`, and `DisplayCard`. These components work together to 
parse user commands, filter relevant data, and update the user interface accordingly.

#### Key Components:

1. `DisplayCommand` and `DisplayCommandParser`:

- `DisplayCommandParser` parses the user input to extract search terms and constructs a `DisplayCommand` with a 
predicate that encapsulates these terms.
- `DisplayCommand` uses this predicate to filter the displayed data in the model. The command interacts with the model
to update the list of persons to those that match the criteria specified by the predicate.

2. `DisplayListPanel`:

- This UI component is responsible for displaying the list of persons that match the search criteria. It utilizes
`ListView` and custom `ListCell` implementations to render the filtered list.
- The `DisplayListPanel` is updated whenever the `DisplayCommand` alters the list of persons in the model to show only 
those that match the search criteria.

3. `DisplayCard`: 

- Each `DisplayCard` represents a single person in the `DisplayListPanel`. It formats and shows detailed information 
about a person, such as their name, phone number, and any other relevant details.
- The `DisplayCard` updates whenever a new person is selected or the displayed list changes.

#### Implementation Details

Command Parsing and Execution:

- The `DisplayCommandParser` reads the input from the user and uses it to instantiate a `DisplayCommand` with the 
appropriate matching criteria.
- `DisplayCommand` then interacts with the model to filter the data based on the provided predicate. If the 
predicate results in one or more matches, the `DisplayListPanel` is updated to show these matches.

UI Updates:

- `DisplayListPanel` listens for changes in the model's filtered list. When `DisplayCommand` updates this list, 
`DisplayListPanel` reacts by refreshing its contents, using `DisplayCard` for each item in the filtered list.
- Each `DisplayCard` extracts and displays information from the Person instance it represents, providing a 
visual representation of each matched person.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of clients for social work, that is dynamically changing
* has a need to efficiently track cases and get important information at a glance
* has a need to easily add notes and observations during visits to record important details
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* 

**Value proposition**: Manage and view client information more efficiently than a standard word-editor/address book/spreadsheet


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                         | I want to …​                 | So that I can…​                                                              |
|----------|---------------------------------|------------------------------|------------------------------------------------------------------------------|
| `* * *`  | social worker                   | see usage instructions       | refer to instructions when I forget how to use the App                       |
| `* * *`  | social worker                   | create a new client          | keep track of their information efficiently                                  |
| `* * *`  | social worker                   | delete a client              | remove client entries that I no longer need                                  |
| `* * *`  | social worker                   | find a client by name        | locate details of clients without having to go through the entire list       |
| `* * *`  | social worker                   | view a client's information  | review and prepare for upcoming appointments                                 |
| `* * *`  | social worker                   | take notes during meetings   | document clients' needs and concerns                                         |
| `* * *`  | social worker                   | schedule meetings            | keep track of all appointments and ensure there are no scheduling conflicts  |
| `* * *`  | social worker                   | delete meetings              | remove meetings that have been cancelled or completed                        |
| `* *`    | social worker                   | hide private contact details | minimize chance of someone else seeing them by accident                      |
| `*`      | social worker with many clients | sort persons by name         | locate a person easily                                                       |
| `*`      | social worker with colleagues   | switch between profiles      | manage my own set of clients on the same machine                             |
| `*`      | social worker                   | undo and redo my commands    | easily rectify a mistaken command                                            |



### Use cases

(For all use cases below, the **System** is `ConnectCare` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Add a client

**MSS**

1.  User requests to add a client
2.  ConnectCare adds the client

    Use case ends.

**Extensions**

* 1a. The details of the client is incorrect

    * 1a1. ConnectCare shows an error message.

        Use case ends.

#### Use case: Update client details

**MSS**

1.  User requests to update a client 
2.  ConnectCare updates the client with new details

    Use case ends.

**Extensions**

* 1a. The client to update is not found

    * 1a1. ConnectCare shows an error message.

      Use case ends.

* 1b. The client details given to update is incorrect

    * 1b1. ConnectCare shows an error message.

      Use case ends.

#### Use case: Delete a person

**MSS**

1.  User requests to list persons
2.  ConnectCare shows a list of persons
3.  User requests to delete a specific person in the list
4.  ConnectCare deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ConnectCare shows an error message.

      Use case resumes at step 2.

#### Use case: Find client

**MSS**

1.  User requests to find a client
2.  ConnectCare lists all clients that match the keyword

    Use case ends.

**Extensions**

* 1a. There is no given keyword.

    * 1a1. ConnectCare shows an error message.

      Use case ends.


#### Use case: Clear all clients

**MSS**

1.  User requests to clear all clients
2.  ConnectCare requests for confirmation
3.  User confirms
4.  ConnectCare clears all clients

    Use case ends.

**Extensions**

* 1a. The list is empty.

    * 1a1. ConnectCare shows an error message.

      Use case ends.

* 3a. The user does not confirm

    * 3a1. ConnectCare informs user of the cancellation

      Use case ends.


#### Use case: Schedule an event

**MSS**

1.  User requests to schedule an event
2.  ConnectCare schedules the event

    Use case ends.

**Extensions**

* 1a. The details of the event are incorrect

    * 1a1. ConnectCare shows an error message.

      Use case ends.

#### Use case: Delete an event

**MSS**

1.  User requests to delete an event
2.  ConnectCare deletes the event

    Use case ends.

**Extensions**

* 1a. The details of the event are incorrect

    * 1a1. ConnectCare shows an error message.

      Use case ends.


#### Use case: Undo a command

**MSS**

1.  User requests to undo a command
2.  ConnectCare undoes the command
3.  ConnectCare restores the application to the previous state

    Use case ends.

**Extensions**

* 1a. There are no more commands to undo

    * 1a1. ConnectCare shows an error message.

      Use case ends.

#### Use case: Redo a command

**MSS**

1.  User requests to redo a command
2.  ConnectCare redoes the command
3.  ConnectCare restores the application to the desired state

    Use case ends.

**Extensions**

* 1a. There are no more commands to redo

    * 1a1. ConnectCare shows an error message.

      Use case ends.

#### Use case: Shortcut (Up-Arrow Key) Display previous command

**MSS**

1.  User requests to display a previous command
2.  ConnectCare displays the command

    Use case ends.

**Extensions**

* 1a. There are no more commands to display

    * 1a1. ConnectCare shows an error message.

      Use case ends.

#### Use case: Shortcut (Down-Arrow Key) Display next command

**MSS**

1.  User requests to display next command
2.  ConnectCare displays the command

    Use case ends.

**Extensions**

* 1a. There are no more commands to display

    * 1a1. ConnectCare shows an error message.

      Use case ends.


#### Use case: Exit the application

**MSS**

1.  User requests to exit the application
2.  ConnectCare exits

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  The system should be responsive, with a maximum response time of 3 seconds for any user action.
4.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5.  The user interface should be intuitive and easy to navigate, requiring minimal training for social workers to use effectively.
6.  The system should work without access to the internet.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Command Line Interface (CLI)**: A CLI is a text-based interface used to run programs, manage computer file sand interact with the computer
* **Main Success Scenario (MSS)**: The primary sequence of steps in a use case that describes the ideal path of interaction between a user and the system without encountering any errors
* **Clients**: People that social workers who are using this application want to keep contact of
* 
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.


### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.


### Displaying a person

1. Displaying a person by name

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `display John Doe`<br>
       Expected: Displays all details associated with "John Doe" in the dedicated display area of the GUI. The command should result in showing details such as name, phone, email, address, etc., that match "John Doe".

    1. Test case: `display Jane`<br>
       Expected: Since there is no client named Jane, no details are shown in the display area. An error message should be shown indicating that no person matches the name "Jane".
   
    1. Test case: `display`<br>
       Expected:  No details are shown in the display area. An error message should be shown indicating the incorrect command format or reminding to enter the name of the person to display.

### Undoing a command

1. Undoing a command when no commands have been entered

   1. Prerequisites: No commands have been entered and the application has just been initialised.
   
   2. Test case: `undo` <br>
        Expected: No changes to person or event lists. Error shown in the result display. No more history to rollback.
   
2. Undoing a command when no reversible commands have been entered

    1. Prerequisites: No reversible commands have been entered. For a full list of reversible commands see the [implementation section for undo](#undoredo-feature)

    2. Test case: `undo` <br>
       Expected: No changes to person or event lists. Error shown in the result display. No more history to rollback.


3. Undoing a command when reversible commands have been entered

   1. Prerequisites: Reversible commands have been entered. For a full list of reversible commands see the [implementation section for undo](#undoredo-feature)

   2. Test case: `undo` while there are commands to revert <br>
        Expected: Application reverts back to the state before the reversible commands are executes. Success message shown on result display. This continues until there are no more commands to revert.
    
   3. Test case: `undo` when there are no commands to revert (start state) <br>
        Expected: Application has been reverted back to the start state. Error shown in the result display. No more history to rollback.


### Redoing a command

1. Redoing a command when no commands have been entered

    1. Prerequisites: No commands have been entered and the application has just been initialised.

    2. Test case: `redo` <br>
       Expected: No changes to person or event lists. Error shown in the result display. No more history to roll forward.


2. Redoing a command when reversible commands have been entered

    1. Prerequisites: Reversible commands have been entered. For a full list of reversible commands see the [implementation section for undo](#undoredo-feature)

    2. Test case: `undo` while there are commands to revert and then `redo` <br>
       Expected: Application reverts back to the desired state before the `undo`. Success message shown on result display.

    3. Test case: `redo` when there are no "`undo`" commands to revert <br>
       Expected: Application remains in current state. Error shown in the result display. No more history to forward.
    
    4. Test case: `undo` while there are commands to revert and then execute a command (that is not `redo`). Next execute `redo` <br>
       Expected: Application remains in current state. Error shown in the result display. No more history to roll forward. This is due to the states being truncated.


### Saving data

1. Dealing with missing/corrupted data files on start-up

   1. Test case: Edit the `addressbook.json` and add or remove fields or add special characters that are not allowed. <br>
        Expected: The application will initialise but with an empty persons list instead. Logger will log a warning
   
   2. Test case: Edit the `calendar.json` and add or remove fields or add special characters that are not allowed. <br>
        Expected: The application will initialise but with an empty events list instead. Logger will log a warning.
   
2. Dealing with missing/corrupted data files while application is running

    1. Test case: Edit the `addressbook.json` and add or remove fields or add special characters that are not allowed. <br>
       Expected: The application will overwrite the changes made with the correct details, and will continue as per normal

    2. Test case: Edit the `calendar.json` and add or remove fields or add special characters that are not allowed. <br>
       Expected: The application will overwrite the changes made with the correct details, and will continue as per normal

### Planned Enhancements

Team size: 5

#### Enhancement 1: More robust `Names`

Feature Flaw/Bug: <br>
Currently, all names must be unique and must only contain alphanumeric characters. This means different languages and special characters are not allowed.

Proposed Enhancement: <br>
Change the restrictions on the regex for the Name class to allow for more flexible names

Justification: <br>
This allows for a more realistic application where names can contain "/" characters, and chracters from other languages.


#### Enhancement 2: More robust `clear` and `find` for `schedule` commands

Feature Flaw/Bug: <br>
Currently, no command for users to clear or find events quickly.

Proposed Enhancement: <br>
Add commands that allows users to clear or find events quickly.

Justification: <br>
This allows for a more user-friendly experience.

#### Enhancement 3: More robust date formats for `schedule add`

Feature Flaw/Bug: <br>
Currently, there is only one format for the date for the `schedule add` command.

Proposed Enhancement: <br>
Add more formats that we can accept for the `schedule add` command like `dd/MM/yyyy HHmm` or more human-readable formats like `dd/MMM/yyyy HHmm`

Justification: <br>
This caters to a wider audience as people from different regions use different date-time formats.

#### Enhancement 4: More functionality to the NOK field

Feature Flaw/Bug: <br>
Currently, the NOK field is just a string representation that is alphanumeric and has the same constraints as `Name`.

Proposed Enhancement: <br>
Add more structure in the code for the NOK field (give it its own class) and allow users to add special characters and validate different aspects of the NOK object (NOK phone number, NOK email should have their own constraints)

Justification: <br>
This is more realistic and useful as NOK details can now be more detailed. This ensures that important information can be communicated effectively in case of emergencies or other situations involving the person associated with the NOK details.

#### Enhancement 5: When `display` throws an error and is incompatible with `undo`/`redo`

Feature Flaw: <br>
Once the user partially types a client’s name and receives an error message when using the display command, the history is reset and the undo/command utils can’t revert to a previous state.

Propose doing Enhancement: <br>
Ensure the command history isn’t affected when an error is thrown in the display command

Justification: <br>
This enhancement allows users to revert back to a previous state even if they face an error when trying to display a client

#### Enhancement 6: Find command error

Feature Flaw/Bug: <br>

Proposed Enhancement: <br>

Justification: <br>
