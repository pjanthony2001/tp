---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

<p align="left">
  <img src="images/Logo.png" width="200"/>
</p>

# ConnectCare User Guide

<box type="info" seamless>


_This page is for ConnectCare users. If you're a developer, see also the
[Developer guide](https://ay2324s2-cs2103t-w12-4.github.io/tp/DeveloperGuide.html)._
</box>

<!-- * Table of Contents -->
## Table of Contents
+ [ConnectCare User Guide](#connectcare-user-guide)
  + [Introduction](#introduction)
  + [Who ConnectCare is For](#who-connectcare-is-for)
  + [Who This Guide is For](#who-this-guide-is-for)
  + [How to Use This Guide](#how-to-use-this-guide)
+ [Quick Start](#quick-start)
    + [Installation Instructions](#installation-instructions)
    + [Startup Instructions](#startup-instructions)
+ [CLI Guide](#cli-guide)
  + [Structure](#structure)
  + [Notes about the command format](#notes-about-the-command-format)
+ [Features](#features)
    + [Client Management](#client-management)
        + [Adding a Client](#adding-a-client-add)
        + [Deleting a Client](#deleting-a-client-delete)
        + [Updating a Client](#updating-a-client-update)
        + [Listing all Clients](#listing-all-clients-list)
        + [Finding Clients](#finding-clients-find)
        + [Displaying a client's information](#displaying-a-client-s-information-display)
        + [Clearing all entries](#clearing-all-entries-clear)
    + [Appointment Management](#appointment-management)
        + [Adding an Appointment](#adding-an-appointment-schedule-add)
        + [Deleting an Appointment](#deleting-appointment-schedule-delete)
    + [Program Controls](#program-controls)
      + [Undoing a Command](#undoing-a-command-undo)
      + [Redoing a Command](#redoing-a-command-redo)
      + [Shortcuts](#shortcuts)
    + [Miscellaneous Commands](#miscellaneous-commands)
      + [Viewing Help](#viewing-help-help)
      + [Exiting the Program](#exiting-the-program-exit)
    + [Saving the data](#saving-the-data)
+ [Frequently Asked Questions](#faq)
+ [Command Summary](#command-summary)
+ [Glossary](#glossary)


___

## Introduction
Welcome to the *ConnectCare User Guide*, your comprehensive resource for managing client contacts with ease!
Designed to help you handle demanding caseloads, *ConnectCare* empowers you to navigate your client interactions seamlessly. Whether you're troubleshooting issues, just getting started, or looking to enhance your workflow, this guide has you covered.

## Who ConnectCare is For

ConnectCare is designed to help social workers with high caseloads, providing solutions that simplifies client management. Whether you're new to the field or a seasoned pro, ConnectCare offers intuitive features that prioritize efficiency, accuracy, and productivity. 
Furthermore, ConnectCare boasts a range of benefits that directly address the needs of social workers, including: 

* **Increased Efficiency:** Spend less time on software and more time directly helping clients.
* **Reduced Errors:** Minimize errors caused by switching between input methods.
* **Boosted Productivity:** Complete tasks faster and free up valuable time for client interaction.

##### Embrace a Keyboard-Centric Approach

Social workers often spend significant time documenting and updating client information.  By minimizing the use of your mouse, ConnectCare allows for a smoother workflow, especially when working remotely or on laptops.

This approach can significantly reduce:

* Hand fatigue and strain.
* Cognitive load from switching between input methods.
* Overall time spent completing tasks.

##### Work Offline, Stay Productive

ConnectCare doesn't need constant internet. This means you can update client information even in areas with spotty reception. Perfect for home visits, field work, or anywhere life takes you.

##### Sensitive Data is kept Secure

ConnectCare takes data security seriously. Unlike some systems, ConnectCare stores your client information directly on your device, not on the internet. This reduces the risk of unauthorized access, keeping your client information safe and secure.

##### Focus on Your Clients, Not Software

ConnectCare simplifies tasks, freeing up your time for what truly matters - building relationships with your clients. Respond faster, deliver better care, and make a real difference in their lives.
___

## Who This Guide Is For

This guide caters to users with varying levels of technical proficiency, requiring only basic keyboard typing skills. Whether you're new to ConnectCare or seeking to enhance your skills, this guide provides comprehensive instructions on all functionalities and commands. From basic navigation to advanced features, this guide serves as your definitive resource for optimizing your usage of ConnectCare.

___

## How to Use This Guide

There is no wrong way to read this guide: if you want to skip ahead, go for it! Each section is contained entirely in itself, and you can find what you are looking for without needing to read the previous sections.


This guide has 4 main sections:
1. The [**Quick Start**](#quick-start) section, we will walk through you in how to get started installing as well as running the ConnectCare on your own desktop.

2. The [**Features Section**](#features) will provide a comprehensive, easy-to-understand guide on how to fully make use of all of ConnectCare's functionalities and commands.

3. The [**FAQ**](#faq) section will answer commonly asked questions, addressing potential further queries you might have!

4. Lastly, the [**Glossary**](#glossary) contains a list of key terms and definitions that might be helpful.

The various boxes used in this guide:
<box type="tip">

Gives you useful tips that are not entirely necessary to use the application, but help you get the most out of _ConnectCare_.
</box>

<box type="info">

Provides additional information and context that extends your understanding of _ConnectCare_'s features.
</box>

<box type="warning">

Important warnings that you should keep in mind while using this User Guide.
</box>

<box type="warning">

**Warning:** If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

___

# Quick start
<IMPROVE BY ADDING SCREENSHOTS AND LINKS>

This section allows you to start caring for your clients as soon as possible!
It will help you [install](#installation-instructions) and [start](#startup-instructions) using the application as fast as possible.

## Installation instructions
1. To use ConnectCare you will require Java 11 to be installed in your computer.

**If you have Java 11 installed**

2. Check that it is the correct version
    - Windows or macOS users might find [this guide](https://blog.hubspot.com/website/check-java-verison) useful.
    - Linux users might find [this guide](https://phoenixnap.com/kb/check-java-version-linux) useful.
    - If you have the correct version skip ahead to step 4, otherwise, follow step 3 to install Java 11.

**If you do _not_ have Java 11 installed**

3. Install Java 11 based on your Operating System
   - **Windows** users should use [this guide](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA). 
   - **MacOS** users should use [this guide](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE).
   - **Linux** users should use [this guide](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-linux-platforms.html#GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8).

4. Download the latest release of `connectcare.jar` [here](https://github.com/AY2324S2-CS2103T-W12-4/tp/releases).

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/quickstart/latest-release.png" alt="jar_file">
        <markdown> Find the latest release with the 'Latest tag' </markdown>
    </pic>
    <pic src="images/quickstart/jar-file.png" alt="jar_file">
        <markdown> Click on the `.jar` file at the bottom of the page to download it </markdown>
    </pic>
</div>

5. Copy the jar file to the folder you want to use as the home folder.

## Startup instructions

6. Double-click on the `connectcare.jar` file to start the application. 
   - If you are facing issues, you can consult [this guide](https://www.wikihow.com/Run-a-.Jar-Java-File).
   - **macOS** users might experience a pop-up that says "connectcar.jar cannot be opened because it is from an unidentified developer." To solve this issue, please consult [this Apple guide](https://support.apple.com/en-sg/guide/mac-help/mh40616/mac)
___

# CLI guide
Our application uses a [CLI](#cli) and primarily, running commands is how users interact with the application. 

## Structure
Each command has the general structure:
1. Main command
2. Parameters

To enter a command:
1. Click on the text box with the placeholder text `Enter command here...`
2. Type in your command, which is made up of a main command, followed by parameters if applicable. Here we have the command `add n/Derek Lau p/92784935 e/dlau@example.com` entered into the command box.

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/cli_tutorial/cli_tutorial_1.png" alt="cli">
        <markdown> Figure 1.1: Application on startup </markdown>
    </pic>
    <pic src="images/user-guide/cli_tutorial/cli_tutorial_2.png" alt="cli">
        <markdown> Figure 1.2: Application with command in textbox </markdown>
    </pic>
</div>

3. Press the `ENTER` key and voilà! You have added your first client with the CLI! 

<div class="image-container" align="middle" style="margin:auto;max-width:50%;">
    <pic src="images/user-guide/cli_tutorial/cli_tutorial_3.png" alt="cli">
        <markdown> Figure 1.3: Application after command is executed </markdown>
    </pic>
</div>

## Notes about the command format
-   In this guide, words in `UPPER_CASE` are the parameters to be supplied by the user.
    e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

-   Parameters in square brackets are optional.
    e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

-   Parameters with `…` after them can be used multiple times including zero times.
    e.g. `[t/TAG]…` can be used as (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

-   Parameters can be in any order.
    e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

-   Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

---

You're all set to use the CLI to its full potential! Read further in our [Features Section](#features) to find out about all the various features and commands that the ConnectCare application offers!
Or jump straight to the [Command Summary](#command-summary) if you are already familiar with the features!

---

# Features

## Client Management
Connect care provides the following commands to help you manage your clients:
* Adding a client: `add`
* Deleting a client: `delete`
* Updating a client: `update`
* Listing all clients: `list`
* Finding clients: `find`
* Displaying a client's information: `display`
* Clearing all entries: `clear`

### Adding a client: `add`
_This command adds a new client to your client list._

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [k/NEXT_OF_KIN] [d/DESCRIPTION] [t/TAG]…`

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter      | Description                                        | Remarks                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|----------------|----------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| n/NAME         | Name of client that you want to add                | Name must be unique, alphanumeric and case-sensitive. It is also compulsory                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| p/PHONE_NUMBER | Phone number of client that you want to add        | Phone number should only contain numbers and is compulsory                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| e/EMAIL        | Email address of client that you want to add       | Emails should be of the format local-part@domain and adhere to the following constraints:<br/>1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.<br/>2. This is followed by a '@' and then a domain name. The domain name is made up of one or more domain labels separated by periods.<br/>The domain name must:<br/>  - end with a domain label at least 2 characters long<br/>  - have each domain label start and end with alphanumeric characters<br/>  - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.<br/> - Valid emails include: PeterJack_1190@example.com, felix@asd, e1234567@u.nus.edu |
| a/ADDRESS      | Address of client that you want to add             | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| k/NEXT_OF_KIN  | Next of Kin details of client that you want to add | Name must be alphanumeric                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| d/DESCRIPTION  | Description of client that you want to add         | Description should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| t/TAG          | Tag to identify the client                         | A person can have any number of tags (or even 0). Tags should be alphanumeric and should not contain spaces or other special characters.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |


<box type="tip" seamless>
  
**Tip:** If you have difficulty adding clients, check if you have the correct prefixes

</box>

<box type="tip" seamless>

**Tip:** If you want tags containing multiple words, consider using camel case (e.g. `t/fallRisk`).

</box>

<box type="tip" seamless>

**Tip:** You can use spaces in place of special characters in the names you want to enter (for the NAME or NEXT_OF_KIN parameters).

</box>
</markdown>
</panel>

&nbsp;

**Examples:**
-   `add n/John Doe p/98765432 e/johnd@example.com`
-   `add n/Betsy Crowe t/friend d/Takes anxiety medication k/Bethany Crowe e/betsycrowe@example.com a/Blk 684A Jurong West Central 1 #10-124 p/92748321 t/lowPriority`
-   `add n/Aaron James p/84362143 e/billj@example.com a/400 Balestier Road #02-27 Balestier Plaza t/highPriority k/John Doe d/Has low blood pressure`

**Walkthrough:**

The screenshots below are what you would expect when using the `add` command. In this example, after using the [`list`](#listing-all-persons-list) command,
the full clients list is displayed. Thereafter, the `add` command was used, using the parameters `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 k/Joe Doe d/Has a history of memory loss t/mentalIllness t/owesMoney`.
After execution, the client `John Doe` is added successfully with the corresponding parameters!

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/add_command/add_command_before.png" alt="add">
        <markdown> Figure 1.1: Before the `add` command is executed </markdown>
    </pic>
    <pic src="images/user-guide/add_command/add_command_after.png" alt="add">
        <markdown> Figure 1.2: After the `add` command is executed </markdown>
    </pic>
</div>

&nbsp;

What if the command was used with invalid parameters? In the example on the left, the command `add n/Matthew Kit` was used, which does not contain all the compulsory parameters.
Here the error message shown tells us that our command format is invalid and shows an example command, which lets you know the correct parameters to use for the command.
In the example on the right, the command `add n/Matthew Kit p/abc65432 e/matk@example.com a/123, Queenstown Ave 2, #03-25 k/Sofie Poe d/Short of hearing` is used, which again is invalid. Here the error message shown is `Phone numbers should only contain numbers, and it should be at least 3 digits long` which lets you know specifically that the phone number given was invalid.
As the commands given were invalid, there would be no changes to the client list.

&nbsp;

<div class="image-container" align="middle" style="display: flex;">
    <pic src="images/user-guide/add_command/add_command_after_without_all_parameters.png" alt="add"> 
        <markdown> Figure 1.3: After the invalid `add` command without all parameters is executed </markdown>
    </pic>
    <pic src="images/user-guide/add_command/add_command_after_with_incorrect_parameters.png" alt="add">
        <markdown> Figure 1.4: After the invalid  `add` command with incorrect parameters is executed </markdown> 
    </pic>
</div>


&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **add** command let's move on to the **delete** command!

---

### Deleting a client: `delete`

_This command allows you to delete a client at a specified `INDEX` from your client list._

**Format:** `delete INDEX`

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter | Description                            | Remarks                                                                                            |
|-----------|----------------------------------------|----------------------------------------------------------------------------------------------------|
| INDEX     | Index of the client you want to delete | Index needs to be a number 1 or greater and cannot be more than the number of clients in your list |
</markdown>
</panel>

&nbsp;

**Examples:**
- `delete 1` would delete the first client in your client list.
- `delete 2` would delete the second client in your client list.

**Walkthrough**:

The screenshots below are what you would expect when using the `delete` command. In this example, after using the `list` command,
the full clients list is displayed. Thereafter, the `delete 3` command was used, removing `John Doe`, the first person in the list, from the list of client contacts.

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/delete_command_before.png" alt="delete">
        <markdown> Figure 1.1: Before the `delete 3` command is executed </markdown>
    </pic>
    <pic src="images/user-guide/delete_command_after.png" alt="delete">
        <markdown> Figure 1.2: After the `delete 3` command is executed </markdown>
    </pic>
</div>

&nbsp;

What if the command was used with an incorrect index? In the example on the left, `delete 0` command was used, which is an invalid index.
Here the error message shown is `Invalid command format! ... Parameters: INDEX (must be a positive integer)` which lets you know to retype the command with a larger index value.
In the example on the right, `delete 4` is used, which again is invalid. Here the error message shown is `The person index provided is invalid` which lets you know to retype the command with a lower index.
As the commands given were invalid, there would be no changes to the client list.

&nbsp;

<div class="image-container" align="middle" style="display: flex;">
    <pic src="images/user-guide/delete_command_after_invalid_0.png" alt="delete"> 
        <markdown> Figure 1.3: After the invalid `delete 0` command is executed </markdown>
    </pic>
    <pic src="images/user-guide/delete_command_after_invalid_5.png" alt="delete">
        <markdown> Figure 1.4: After the invalid  `delete 4` command is executed </markdown> 
    </pic>
</div>

&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **delete** command let's move on to the **update** command!

---

### Updating a client: `update`

_This command helps update existing client's information in the client list in the event that something changes._

**Format:** `update u/EXISTING_USER [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DESCRIPTION] [k/NEXTOFKIN] [t/TAG]…`

<box type="info">

**Note:** At least one optional parameter has to be given.
</box>

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter       | Description                                        | Remarks                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|-----------------|----------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| u/EXISTING_USER | Name of client that you want to update             | Name must be unique, alphanumeric and case-sensitive. It is also compulsory                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| n/NAME          | New name of client that you want to add            | Name must be unique, alphanumeric and case-sensitive                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| p/PHONE_NUMBER  | Phone Number of client that you want to add        | Phone number should only contain numbers                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| e/EMAIL         | Email of client that you want to add               | Emails should be of the format local-part@domain and adhere to the following constraints:<br/>1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.<br/>2. This is followed by a '@' and then a domain name. The domain name is made up of one or more domain labels separated by periods.<br/>The domain name must:<br/>  - end with a domain label at least 2 characters long<br/>  - have each domain label start and end with alphanumeric characters<br/>  - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.<br/> - Valid emails include: PeterJack_1190@example.com, felix@asd, e1234567@u.nus.edu |
| a/ADDRESS       | Address of client that you want to add             | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| k/NEXT_OF_KIN   | Next of Kin details of client that you want to add | Name must be alphanumeric                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| d/DESCRIPTION   | Description of client that you want to add         | Description should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| t/TAG           | Tag to identify the client                         | Multiple tags can be specified, but they must each be prefixed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
</markdown>

<box type="tip" seamless>

**Tip:** You can use spaces in place of special characters in the names you want to enter (for the NAME or NEXT_OF_KIN parameters).

</box>
</panel>

&nbsp;

**Example:**
-   `update u/Jane Doe n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-011` This command updates Jane Doe's information to be as follows: </br>
    - name: John Doe  
    - phone no: 98765432
    - email: [johnd@example.com](mailto:johnd@example.com)  
    - address: John street, block 123, #01-011

**Walkthrough:**

The screenshots below are what you would expect when using the `update` command. In this example, after using the `list` command,
the full clients list is displayed. Thereafter, the `update` command was used, using the parameters `u/Peter Crow p/94325412 e/petercrow@example.com`.
After execution, the client `Peter Crow` is updated successfully with the corresponding parameters!

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/update_command/update_command_before.png" alt="update">
        <markdown> Figure 1.1: Before the `update command is executed </markdown>
    </pic>
    <pic src="images/user-guide/update_command/update_command_after.png" alt="update">
        <markdown> Figure 1.2: After the `update` command is executed </markdown>
    </pic>
</div>

&nbsp;

What if the command was used with invalid parameters? In the example on the left, the command `update u/Unknown Person p/92374832` was used, which specifies a name not in the name list.
Here the error message shown is `The person name provided is invalid` which lets you know that you are trying to update a client that does not exist.
In the example on the right, the command `update u/Peter Crow p/abc123` is used, which again is invalid. Here the error message shown is `Phone numbers should only contain numbers, and it should be at least 3 digits long` which lets you know specifically that the phone number given was invalid.
As the commands given were invalid, there would be no changes to the client list.

&nbsp;


<div class="image-container" align="middle" style="display: flex;">
    <pic src="images/user-guide/update_command/update_command_after_with_invalid_client.png" alt="update"> 
        <markdown> Figure 1.3: After the invalid `update` command with an invalid client is executed </markdown>
    </pic>
    <pic src="images/user-guide/update_command/update_command_after_with_incorrect_parameters.png" alt="update">
        <markdown> Figure 1.4: After the invalid  `update` command with invalid parameters is executed </markdown> 
    </pic>
</div>


&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **update** command let's move on to the **list** command!

---

### Listing all clients: `list`
_This command shows a list of all persons._

**Format:** `list`

**Examples:**

The screenshots below are what you would expect when using the [`list`](#listing-all-persons-list) command. In this example, after using the [`find`](#locating-clients-by-name-find) command,
only `Peter Crow` was shown in the list. Thereafter, the `list` command was used, displaying the entire list of client contacts.
&nbsp;  

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/list_command_before.png" alt="list">
        Figure 1.1: Before the list command is executed
    </pic>
    <pic src="images/user-guide/list_command_after.png" alt="list">
        Figure 1.2: After the list command is executed
    </pic>
</div>
&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **list** command let's move on to the **find** command!

---

### Finding clients: `find`

_This command allows you to find clients in your client list by specifying client parameters that you are interested in._

**Format:** `find [n/NAME]… [a/KEYWORD]… [e/KEYWORD]… [t/KEYWORD]… [k/KEYWORD]… [d/KEYWORD]… [p/KEYWORD]…`

<box type="info">

**Note:** At least one optional parameter has to be given.
</box>

<box type="info">

**Note:** This command finds clients that match any of your search terms.
</box>

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter       | Description                                          | Remarks                                                                         |
|-----------------|------------------------------------------------------|---------------------------------------------------------------------------------|
| n/NAME          | Name or part of name of client that you want to find | Only the letters used in a name needs to match. Lower case letters can be used. |
| p/PHONE_NUMBER  | Phone Number of client that you want to add          | -                                                                               |
| e/EMAIL         | Email of client that you want to add                 | -                                                                               |
| a/ADDRESS       | Address of client that you want to add               | -                                                                               |
| k/NEXT_OF_KIN   | Next of Kin details of client that you want to add   | -                                                                               |
| d/DESCRIPTION   | Description of client that you want to add           | -                                                                               |
| t/TAG           | Tag to identify the client                           | -                                                                               |
</markdown>
</panel>

&nbsp;

**Example:**
- `find p/99824412`
- `find n/xavier k/polly`
- `find n/bobby`

**Walkthrough:**

Let's say your client list contains the following clients:

<div class="image-container" style="display: block;max-width: 60%;margin: auto;">
    <pic src="images/user-guide/ExampleClientList.png" alt="clientlist">
        <markdown> Figure 1.1: _An example client list in ConnectCare (as indicated by the red box)_ </markdown>
    </pic>
</div>

-   `find n/alex` would return `Alex Yeoh` as the client name matches with `alex`.
-   `find n/alex p/99272758 ` would return `Alex Yeoh` as the client name matches with `alex`
    as well as `Bernice Yu` as the client phone number matches with `99272758`.
-   `find p/9` would return `Bernice Yu`, `Charlotte Oliveiro` and `David Li` as their phone numbers all
    start with `9`.

<box type="info">

&nbsp;

**Note:** you can search for multiple keywords, for example `find n/name1 n/name2` would search for all clients with names that contain name1 OR name2
This also applies to the optional keywords, for example `find n/name1 a/address1 a/address2 would search for all clients with names that contain name1 AND
have an address that contains address1 OR address2, for example:
-   `find n/alex n/bernice` would return `Alex Yeoh` as well as `Bernice Yu`
-   `find t/friends t/family` would return `Alex Yeoh`, `Bernice Yu`, and `David Li`
</box>

<box type="tip">

**Tip:**
After searching for clients, you can use the `list` command to view your full list of clients again!
</box>

&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **find** command let's move on to the **display** command!

---

### Displaying a client's information: `display`

_This command allows you to view a client's information in a more detailed manner_

**Format:** `display NAME`

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter | Description                                 | Remarks                                  |
|-----------|---------------------------------------------|------------------------------------------|
| NAME      | Full name of the client you want to display | Name must be present in the client list  |
</markdown>
</panel>

&nbsp;

**Examples:**

`display Peter Crouch` would display all of Peter's information as a contact card on the application.

**Walkthrough:**

The screenshots below are what you would expect when using the `display` command. In this example, after using the `list` command,
the full clients list is displayed. Thereafter, the `display Peter Crouch` command was used, displaying Peter. The cursor automatically moves
to the description box, allowing you to make changes to Peter's details. After making the necessary changes, hit enter. The details will be updated
and you will be redirected back to the home page with the clients list. At no stage do you need to reach for the mouse!

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/display_command_before.png" alt="list">
        Figure 1.1: Before the display command is executed
    </pic>
    <pic src="images/user-guide/display_command_after.png" alt="list">
        Figure 1.2: After the display command is executed
    </pic>
</div>

<box type="warning">

**Caution:**
While you are in the display view, you cannot enter any commands in the command box other than the [list command](#listing-all-persons-list) which will swap you back into the list view.
Any changes you make to description by reverting to the list view using this method would not be saved. Alternatively, you can the press ENTER key and the [undo command](#undoing-a-command-undo) to revert the changes.
If you would like to resume entering commands, press the ENTER key in the description box (which will save your changes) to return to the list view.
</box>

&nbsp;
Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **display** command let's move on to the **clear** command!

### Clearing all entries : `clear`

_This command allows you to purge your client list, removing **ALL** clients in your client list._

Format: `clear`

<box type="tip">

**Tip:**
Accidentally cleared your client list? Worry not, the `undo` feature might be able to help you get it back!
</box>

&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **Client management** features let's move on to the **Appointment management** features!

## Appointment Management
ConnectCare provides the following commands to help you manage your appointments:
* Adding an appointment: `schedule add`
* Deleting an appointment: `schedule delete`

Let's talk about the different **Appointment management** features, starting with **schedule add** command

### Adding an appointment: `schedule add`

_This command allows you to add an appointment with the specified parameters._

**Format:** `schedule add h/HEADING t/TIME d/DESCRIPTION n/CLIENT_NAME `

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter   | Description                               | Remarks                                                                                                                                                                                                                                                                                                                             |
|-------------|-------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| HEADING     | The heading of the appointment            | The heading must contain only alpha-numeric characters and should not be more than 70 characters                                                                                                                                                                                                                                    |
| TIME        | The time of the appointment               | The time of the appointment must conform to this format: M/d/yyyy HHmm, where, month (M), day (d), year(yyyy), time in 24 hour format (HHmm). <br/>Refer to [this Java Documentation article](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) for more information about what this format means. |
| DESCRIPTION | The description of the appointment        | Description should not be blank                                                                                                                                                                                                                                                                                                     |
| CLIENT_NAME | The name of the client in the appointment | The name should contain only alpha-numeric characters and spaces and shouldn't be blank                                                                                                                                                                                                                                             |
</markdown>
</panel>

Once the command is entered, the event should be added to the events panel on the right of the application.

**Examples:**
-   `schedule add h/Meeting with Client t/2/14/2024 0930 d/Discuss project details n/John Doe`
-   `schedule add h/Discharge plan meeting t/02/14/2024 1000 d/Discuss discharge n/Jack Doe`


**Walkthrough:**

The screenshots show what you should expect on your screen while executing this command: `schedule add h/Meeting with Client t/2/14/2024 0930 d/Discuss project details n/John Doe`.
Once the command is entered, the event should be deleted on the events panel on the right of the application.

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/schedule_command/schedule-add.png" alt="schedule-add">
        <markdown> Figure 1.1: Before the `schedule add` command is executed </markdown>
    </pic>
    <pic src="images/user-guide/schedule_command/schedule-add-after.png" alt="add">
        <markdown> Figure 1.2: After the `schedule add` command is executed </markdown>
    </pic>
</div>

&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **schedule add** command let's move on to the **schedule delete** command!

---

### Deleting Appointment : `schedule delete`

_This command allows you to remove an appointment with the specified parameters._

**Format:** `schedule delete h/HEADING`

<panel header="Parameter Descriptions and Remarks" alt="Parameters" minimized>
<markdown>

| Parameter   | Description                               | Remarks                                                           |
|-------------|-------------------------------------------|-------------------------------------------------------------------|
| HEADING     | The heading of the appointment            | Heading must be present in the client list and is case sensitive. |
</markdown>
</panel>

Once the command is entered, the event should be removed from the events panel on the right of the application.

<box type="tip">

**Tip:** Make sure there are no extra spaces between words in the heading.
</box>

**Examples:**
-   `schedule delete h/Meeting with Client`
-   `schedule delete h/Discharge plan meeting`

<box type="info">

**Note:** It is only possible to delete an event if the heading exists in the events panel on the right of the application.
</box>

**Walkthrough:**

The screenshots show what you should expect on your screen while executing this command: `schedule delete h/Client Checkin`.
Once the command is entered, the event should be added to the events panel on the right of the application.

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/schedule_command/schedule-delete.png" alt="schedule-delete">
        <markdown> Figure 1.1: Before the `schedule delete` command is executed </markdown>
    </pic>
    <pic src="images/user-guide/schedule_command/schedule-delete-after.png" alt="schedule-delete">
        <markdown> Figure 1.2: After the `schedule delete` command is executed </markdown>
    </pic>
</div>

&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **Appointment management** features let's move on to the **Program controls**!

---

## Program Controls
ConnectCare provides the following commands for navigating the application:
* Undoing a command: `undo`
* Redoing a command: `redo`
* Shortcuts
* `up` arrow
* `down` arrow

Let's talk about the different **Program controls**, starting with the **undo** command

### Undoing a command : `undo`

_This command allows you to undo your last command, restoring the ConnectCare application to its previous state._

Format: `undo`

**Walkthrough:**

The screenshots below are what you would expect when using the `undo` command.

In this example, after using the `add` command to add a new client `Charlie`, I wish to undo this addition as bertrand will not be assigned to me.
As seen in the second image, after using the `undo` command, Charlie is no longer present in the client list as his addition has been undone.
&nbsp;

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/undo_command_before1.png" alt="list">
        Figure 1.1: Before the undo command is executed
    </pic>
    <pic src="images/user-guide/undo_command_after1.png" alt="list">
        Figure 1.2: After the undo command is executed
    </pic>
</div>
&nbsp;

<box type="tip">

**Tip:**
The undo command does not undo every single command, only those that change the ConnectCare application in a significant way.
If you are at the earliest state (i.e. you can't undo any command) an appropriate error message will be displayed: "You cannot rollback the state anymore!"
For a comprehensive deep-dive into the undo command, please refer to the [Implementation section of our Developer Guide](https://ay2324s2-cs2103t-w12-4.github.io/tp/DeveloperGuide.html)

</box>

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **undo** command let's move on to the **redo** command!

---

### Redoing a command : `redo`

_This command allows you to redo your command, reversing any changes that were previously undone._

Format: `redo`

**Walkthrough:**

The screenshots below are what you would expect when using the `redo` command.

In this example, after undoing an update of my client's name from `Peter Crow` to `Peter Crouch`, we can see that the current client list has his name as `Peter Crow`
As seen in the second image, after using the `redo` command, the change is redone, and the client list shows the client's name as `Peter Crouch`
&nbsp;

<div class="image-container" align="middle" style="display:flex">
    <pic src="images/user-guide/redo_command_before.png" alt="list">
        Figure 1.1: Before the redo command is executed
    </pic>
    <pic src="images/user-guide/redo_command_after.png" alt="list">
        Figure 1.2: After the redo command is executed
    </pic>
</div>
&nbsp;

<box type="tip">

**Tip:**
The redo command does not redo every single command, only those that change the ConnectCare application in a significant way. 
If you are at the most recent state (i.e. you can't redo this command) an appropriate error message will be displayed: "You cannot roll forward the state anymore!"
For a comprehensive deep-dive into the redo command, please refer to the [Implementation section of our Developer Guide](https://ay2324s2-cs2103t-w12-4.github.io/tp/DeveloperGuide.html)

</box>

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **redo** command let's move on to see some command **shortcuts**!

---

### Shortcuts
_These are a list of keyboard actions to better navigate our application_

<box type="tip">

**Tip:** These shortcuts only work when the text box is selected.
</box>


| Action | Description                                    |
|--------|------------------------------------------------|
| `Up`   | Displays the previous command entered, if any. |
| `Down` | Displays the next command entered, if any.     |

<box type="info">

**Note**: A message will be displayed in the dialog "Keyboard Shortcuts: There are no more commands to display!" to notify users when there are no previous/next command available.
</box>

<box type="info">

**Note**: Only commands that have been executed successfully will be saved.
</box>

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **shortcuts** let's move on to explore the **Miscellaneous commands**!

---

## Miscellaneous Commands

### Viewing Help : `help`

_This command displays a popup message with a link to access this User Guide._

Format: `help`

<div class="image-container" align="middle" style="margin:auto;">
    <pic src="images/user-guide/help/help_command.png" alt="help popup">
        Figure 1: Pop-up when the help command is entered
    </pic>
</div>
&nbsp;

Click [here](#table-of-contents) to return to the table of contents!

Now that we are done with the **help** command let's move on to the **exit** command!

---

### Exiting the program : `exit`

_This command allows you to exit the application._

Format: `exit`

<box type="tip">

**Tip:**
You can simply close the window using the X button too!
</box>

Click [here](#table-of-contents) to return to the table of contents!

Let's now understand how data is saved and managed in the application.

---

## Saving the data

Your locally saved client list will be updated after any change is made, so no further action needs to be taken when operating the application! On start-up, your existing client list (if it exists) will also be automatically loaded, so don't worry about that!

<box type="info">

**Note**: If it is your first time running the program, and there is no existing client list, a new file will automatically be generated to store your new client list once changes are made!
</box>

Click [here](#table-of-contents) to return to the table of contents!

___

Congratulations, you've reached the end of all of our current features! If you ever forget any of the commands a quick look at the [command summary](#command-summary) would help!

---

# FAQ

**Q:** How do I transfer my data to another Computer? \
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

Click [here](#table-of-contents) to return to the table of contents!
___

# Command summary

| Action              | Format                                                                                  | Examples                                                                                                                           |
|---------------------|-----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [k/NEXTOFKIN] [d/DESCRIPTION] [t/TAG]… ` | `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 d/Suffers from anxiety k/Jon Ho t/friend t/colleague` |
| **Update**          | `update u/existing user [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`       | `Update u/Jane Doe n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-011`                                    |
| **Find**            | `find [n/NAME]… [a/ADD]… [e/EMAIL]… [t/TAG]… [k/KIN]… [d/DESC]… [p/PHONE]…`             | `find n/James a/clementi e/gmail t/important k/charles d/tall p/123`                                                               |
| **Add Schedule**    | `schedule add h/HEADING t/TIME d/DESCRIPTION n/CLIENT_NAME`                             | `schedule add h/Meeting with Client t/2/14/2024 0930 d/Discuss project details n/John Doe`                                         |
| **Delete Schedule** | `schedule delete h/HEADING`                                                             | `schedule delete h/Meeting with Client`                                                                                            |
| **Undo**            | `undo`                                                                                  |                                                                                                                                    |
| **Redo**            | `redo`                                                                                  |                                                                                                                                    |
| **Display**         | `display NAME`                                                                          |                                                                                                                                    |
| **Clear**           | `clear`                                                                                 |                                                                                                                                    |
| **Exit**            | `exit`                                                                                  |                                                                                                                                    |

Click [here](#table-of-contents) to return to the table of contents!
___

# Glossary

##### CLI
CLI stands for Command Line Interface, which is a way for you to interact with the application with only textual
commands, there is no need for a mouse. Once you learn the commands, CLIs can be faster for repetitive tasks than navigating menus with a mouse.
For more information, see also the [Wikipedia article for CLI](https://en.wikipedia.org/wiki/Command-line_interface)

##### Local
Local applications runs on the same computer it is launched on and there is no program or data that is being ran or transferred to another remote machine.
For more information, see also this [Microsoft article for Local and Remote execution](https://learn.microsoft.com/en-us/sql/integration-services/run-manage-packages-programmatically/understanding-the-differences-between-local-and-remote-execution?view=sql-server-ver16)

Click [here](#table-of-contents) to return to the table of contents!

---
