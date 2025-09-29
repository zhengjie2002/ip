# Tony User Guide

Tony is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI). Tony enables fast
typist to quickly be able to manage their tasks without having to take their hands off the keyboard to use the mouse.
Tony will keep a copy of our tasks as a text file in a directory and restore them when you start him up again, ensuring
that you will always have your task list with you.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    * [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding tasks: `find`](#finding-tasks-find)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `17` installed in your Computer.

2. Download the latest `tony.jar` from [here](https://github.com/zhengjie2002/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Tony task manager.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tony.jar` command to
   run the application.<br>
   A greeting message similar to the below should appear in a few seconds:<br>
   ```
   ____________________________________________________________
   Hello! I'm Tony
   What can I do for you?
   ____________________________________________________________
   ```

5. Type a command in the terminal and press Enter to execute it. e.g. typing **`list`** and pressing Enter will show all
   tasks.<br>
   Some example commands you can try:

    * `list` : Lists all tasks.

    * `todo read book` : Adds a todo task named "read book" to the task list.

    * `deadline assignment /by 15-12-2024` : Adds a deadline task "assignment" due on Dec 15, 2024.

    * `delete 1` : Deletes the 3rd task shown in the current list.

    * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

> [!NOTE]

> Information about command format
>

* Commands are case-sensitive. e.g. `list` is a valid command but `List` or `LIST` are not.
*
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

* Parameters must be provided in the order specified.

* Date format must be `DD-MM-YYYY` (e.g. `15-12-2024` for December 15, 2024).

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example:

* `todo read book`

Expected output:

```
____________________________________________________________
Got it. I've added this task:
[T][ ] read book
Now you have 1 task(s) in the list.
____________________________________________________________
```

### Adding a deadline task: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`

* `DATE` must be in the format `DD-MM-YYYY`

Example:

* `deadline submit assignment /by 15-12-2024`

Expected output:

```
____________________________________________________________
Got it. I've added this task:
[D][ ] submit assignment (by: Dec 15 2024)
Now you have 1 task(s) in the list.
____________________________________________________________
```

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

* Both `START_DATE` and `END_DATE` must be in the format `DD-MM-YYYY`
* The end date must not be before the start date

Example:

* `event project meeting /from 15-12-2024 /to 15-12-2024`

Expected output:

```
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (from: Dec 15 2024 to: Dec 15 2024)
Now you have 1 task(s) in the list.
____________________________________________________________
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Expected output:

```
____________________________________________________________
Here are the Tasks in your list:
1.[T][ ] read book
2.[D][ ] submit assignment (by: Dec 15 2024)
3.[E][ ] project meeting (from: Dec 15 2024 to: Dec 15 2024)
____________________________________________________________
```

### Marking a task as done: `mark`

Marks the specified task as completed.

Format: `mark INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* Any index that is out of bounds (e.g. 0, -1, 100 when there are only 3 tasks) will be rejected and an error message
  will be shown.
* The task must not be currently marked as done. If the task is already done, an error message will be shown.

Examples:

* `mark 2` marks the 2nd task in the task list as done.

Expected output:

```
____________________________________________________________
Nice! I've marked this task as done:
 [T][X] read book
____________________________________________________________
```

### Unmarking a task: `unmark`

Marks the specified task as not completed.

Format: `unmark INDEX`

* Marks the task at the specified `INDEX` as not done.
* The index refers to the index number shown in the displayed task list.
* Any index that is out of bounds (e.g. 0, -1, 100 when there are only 3 tasks) will be rejected and an error message
  will be shown.
* The task must be currently marked as done. If the task is already not done, an error message will be shown.

Examples:

* `unmark 2` marks the 2nd task in the task list as not done.

Expected output:

```
____________________________________________________________
OK, I've marked this task as not done yet:
 [T][ ] read book
____________________________________________________________
```

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* Any index that is out of bounds (e.g. 0, -1, 100 when there are only 3 tasks) will be rejected and an error message
  will be shown.

Examples:

* `delete 2` deletes the 2nd task in the task list.

Expected output:

```
____________________________________________________________
Noted. I've removed this task:
 [T][ ] read book
Now you have 2 task(s) in the list.
____________________________________________________________
```

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

* The search is not case-sensitive. e.g `book` will match `Book`
* Only the task description is searched.
* Tasks matching the keyword will be displayed.

Example:

* `find book` returns tasks containing "book" in their description

Expected output:

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] read book
2.[T][ ] buy book
____________________________________________________________
```

### Exiting the program: `bye`

Exits the program and saves all tasks to the data file.

Format: `bye`

Expected output:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Follow the [Quick Start](#quick-start) guide to get Tony on another computer. After that, overwrite the empty
data
file it creates with the file that contains the data of your previous Tony home folder. The data file is located at
`./data/tony.txt`.

**Q**: What happens if I enter an invalid date format?<br>
**A**: Tony will display an error message asking you to use the correct date format `DD-MM-YYYY`.

**Q**: Can I edit a task after adding it?<br>
**A**: Currently, Tony does not support editing tasks. You can delete the task and add a new one with the correct
information.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

 Action           | Format, Examples                                                                                             
------------------|--------------------------------------------------------------------------------------------------------------
 **Add Todo**     | `todo DESCRIPTION` <br> e.g., `todo read book`                                                               
 **Add Deadline** | `deadline DESCRIPTION /by DATE` <br> e.g., `deadline submit assignment /by 15-12-2024`                       
 **Add Event**    | `event DESCRIPTION /from START_DATE /to END_DATE` <br> e.g., `event meeting /from 15-12-2024 /to 15-12-2024` 
 **List**         | `list`                                                                                                       
 **Mark**         | `mark INDEX`<br> e.g., `mark 3`                                                                              
 **Unmark**       | `unmark INDEX`<br> e.g., `unmark 3`                                                                          
 **Delete**       | `delete INDEX`<br> e.g., `delete 3`                                                                          
 **Find**         | `find KEYWORD`<br> e.g., `find book`                                                                         
 **Exit**         | `bye`                                                                                                        
